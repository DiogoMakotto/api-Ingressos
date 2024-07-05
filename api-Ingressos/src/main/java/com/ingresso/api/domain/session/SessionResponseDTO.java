package com.ingresso.api.domain.session;

import java.util.Date;
import java.util.UUID;

public record SessionResponseDTO(UUID id, String title, String description, Date date, String city, String state, Boolean remote, String sessionUrl, String imgUrl) {
}
