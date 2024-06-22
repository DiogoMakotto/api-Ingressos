package com.ingresso.api.repositories;


import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdressRepository extends JpaRepository<RabbitConnectionDetails.Address, UUID> {
}
