package com.example.agenda.participant.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name")
    private String name;
}
