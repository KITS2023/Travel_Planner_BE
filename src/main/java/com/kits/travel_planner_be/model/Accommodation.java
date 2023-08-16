package com.kits.travel_planner_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String type;
    @Column(nullable = false)
    private Timestamp checkIn;
    @Column(nullable = false)
    private Timestamp checkOut;
    private String address;
    private double cost;
    private boolean status;
}
