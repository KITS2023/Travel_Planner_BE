package com.kits.travel_planner_be.repository;

import com.kits.travel_planner_be.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d FROM Destination d")
    List<Destination> findDestinationWithLimit(@Param("limit") int limit);
}
