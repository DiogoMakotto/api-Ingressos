package com.ingresso.api.service;

import com.ingresso.api.domain.session.Session;
import com.ingresso.api.domain.session.SessionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class SessionService {
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

        return newSession;
    }
    private String uploadImg(MultipartFile multipartFile){
        return "";
    }
}
