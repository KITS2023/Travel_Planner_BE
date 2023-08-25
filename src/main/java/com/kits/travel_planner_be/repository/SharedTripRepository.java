package com.kits.travel_planner_be.repository;

import com.kits.travel_planner_be.model.SharedTrip;
import com.kits.travel_planner_be.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedTripRepository extends JpaRepository<SharedTrip, Long> {
    @Query("SELECT st.trip from SharedTrip st where st.user.id = :userId")
    List<Trip> getTripsThatUserIsShared(@Param("userId") Long userId);
}
