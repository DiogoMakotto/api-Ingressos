package com.ingresso.api.domain.address;

import com.ingresso.api.domain.session.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "adress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
