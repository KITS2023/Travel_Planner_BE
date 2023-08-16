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
@Table(name = "tb_flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
    @Column(nullable = false)
    private Timestamp startDate;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private String arrival;
    @Column(nullable = false)
    private String transit;
    @Column(nullable = false)
    private String airline;
    private double cost;
}
