package com.kits.travel_planner_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelPlannerBeApplication{

    public static void main(String[] args) {
        SpringApplication.run(TravelPlannerBeApplication.class, args);
    }
}
