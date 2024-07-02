package com.ingresso.api.repositories;

import com.ingresso.api.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository <Address, UUID> {
}
