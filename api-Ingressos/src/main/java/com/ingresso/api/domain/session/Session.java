package com.ingresso.api.domain.session;

import com.ingresso.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "session")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String sessionUrl;
    private boolean remote;
    private Date date;

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private Address address;


}
