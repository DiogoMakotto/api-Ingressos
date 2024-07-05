package com.ingresso.api.controllers;

import com.ingresso.api.domain.session.Session;
import com.ingresso.api.domain.session.SessionDetailsDTO;
import com.ingresso.api.domain.session.SessionRequestDTO;
import com.ingresso.api.domain.session.SessionResponseDTO;
import com.ingresso.api.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Session> create(@RequestParam("title") String title,
                                          @RequestParam(value = "description", required = false) String description,
                                          @RequestParam("date") Long date,
                                          @RequestParam("city") String city,
                                          @RequestParam("state") String state,
                                          @RequestParam("remote") Boolean remote,
                                          @RequestParam("sessionUrl") String sessionUrl,
                                          @RequestParam(value = "image",required = false) MultipartFile image) {
        SessionRequestDTO sessionRequestDTO = new SessionRequestDTO(title, description, date, city, state, remote, sessionUrl, image);
        Session newSession = this.sessionService.createSession(sessionRequestDTO);
        return ResponseEntity.ok(newSession);
    }

    @GetMapping("{sessionId}")
    public ResponseEntity<SessionDetailsDTO> getSessionDetails(@PathVariable UUID sessionId){
        SessionDetailsDTO sessionDetails = sessionService.getSessionDetails(sessionId);
        return ResponseEntity.ok(sessionDetails);
    }



    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> getSessions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<SessionResponseDTO> allSessions = this.sessionService.getUpcomingSessions(page, size);
        return ResponseEntity.ok(allSessions);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SessionResponseDTO>> getFilteredSessions(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                        @RequestParam(required = false) String title,
                                                                        @RequestParam(required = false) String city,
                                                                        @RequestParam(required = false) String uf,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        List<SessionResponseDTO> sessions = sessionService.getFilteredSessions(page, size, city, uf, startDate, endDate);
        return ResponseEntity.ok(sessions);
    }
}

