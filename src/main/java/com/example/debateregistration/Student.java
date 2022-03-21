package com.example.debateregistration;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer studentId;

    @Column
    private String name;

    @Column
    private String department;

    @Column
    private String country;
}
