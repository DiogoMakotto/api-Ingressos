package com.ingresso.api.domain.session;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record SessionRequestDTO(String title, String description, Long date, String city, String state, Boolean remote, String sessionUrl, MultipartFile image) {
}
