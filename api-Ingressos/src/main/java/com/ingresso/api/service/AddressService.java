package com.ingresso.api.service;

import com.ingresso.api.domain.address.Address;
import com.ingresso.api.domain.session.Session;
import com.ingresso.api.domain.session.SessionRequestDTO;
import com.ingresso.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(SessionRequestDTO data, Session session) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setSession(session);

        return addressRepository.save(address);
    }
}
