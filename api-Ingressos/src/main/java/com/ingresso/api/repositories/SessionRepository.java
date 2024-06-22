package com.ingresso.api.repositories;

import com.ingresso.api.domain.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

}
