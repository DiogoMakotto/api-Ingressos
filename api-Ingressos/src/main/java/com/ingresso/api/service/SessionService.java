package com.ingresso.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.ingresso.api.domain.coupon.Coupon;
import com.ingresso.api.domain.session.Session;
import com.ingresso.api.domain.session.SessionDetailsDTO;
import com.ingresso.api.domain.session.SessionRequestDTO;
import com.ingresso.api.domain.session.SessionResponseDTO;
import com.ingresso.api.repositories.AddressRepository;
import com.ingresso.api.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AddressService AddressService;

    @Autowired
    private CouponService couponService;
    @Autowired
    private SessionRepository repository;

    public Session createSession(SessionRequestDTO data){
        String imgUrl = null;

        if (data.image() != null){
            imgUrl = this.uploadImg(data.image());
        }

        Session newSession = new Session();
        newSession.setTitle(data.title());
        newSession.setDescription(data.description());
        newSession.setSessionUrl(data.sessionUrl());
        newSession.setDate(new Date(data.date()));
        newSession.setImgUrl(imgUrl);
        newSession.setRemote(data.remote());

        repository.save(newSession);

        if(!data.remote()) {
            this.AddressService.createAddress(data, newSession);
        }

        return newSession;
    }
    @GetMapping
    public List<SessionResponseDTO> getUpcomingSessions(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Session> sessionPage = this.repository.findUpcomingSessions(new Date(), pageable);
        return sessionPage.map(session -> new SessionResponseDTO(
                        session.getId(),
                        session.getTitle(),
                        session.getDescription(),
                        session.getDate(),
                        session.getAddress() != null ? session.getAddress().getCity() : "",
                        session.getAddress() != null ? session.getAddress().getUf() : "",
                        session.isRemote(),
                        session.getSessionUrl(),
                        session.getImgUrl())
                )
                .stream().toList();
    }

    public List<SessionResponseDTO> getFilteredSessions(int page, int size, String city, String uf, Date startDate, Date endDate){
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date(0);
        endDate = (endDate != null) ? endDate : new Date();

        Pageable pageable = PageRequest.of(page, size);

        Page<Session> sessionPage = this.repository.findFilteredSessions(city, uf, startDate, endDate, pageable);
        return sessionPage.map(session -> new SessionResponseDTO(
                        session.getId(),
                        session.getTitle(),
                        session.getDescription(),
                        session.getDate(),
                        session.getAddress() != null ? session.getAddress().getCity() : "",
                        session.getAddress() != null ? session.getAddress().getUf() : "",
                        session.isRemote(),
                        session.getSessionUrl(),
                        session.getImgUrl())
                )
                .stream().toList();
    }

    public SessionDetailsDTO getSessionDetails(UUID sessionId) {
        Session session = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        List<Coupon> coupons = couponService.consultCoupons(sessionId, new Date());


        List<SessionDetailsDTO.CouponDTO> couponDTOs = coupons.stream()
                .map(coupon -> new SessionDetailsDTO.CouponDTO(
                        coupon.getCode(),
                        coupon.getDiscount(),
                        coupon.getValid()))
                .collect(Collectors.toList());

        return new SessionDetailsDTO(
                session.getId(),
                session.getTitle(),
                session.getDescription(),
                session.getDate(),
                session.getAddress() != null ? session.getAddress().getCity() : "",
                session.getAddress() != null ? session.getAddress().getUf() : "",
                session.getImgUrl(),
                session.getSessionUrl(),
                couponDTOs);
    }

    private String uploadImg(MultipartFile multipartFile){
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try{
            File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, filename, file);
            file.delete();
            return s3Client.getUrl(bucketName, filename).toString();
        }catch (Exception e){
            System.out.println("Erro ao subir arquivo");
            return "";
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }


}
