package com.ingresso.api.domain.address;

import com.ingresso.api.domain.session.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String uf;
    private String city;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

}
