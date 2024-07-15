package com.rocketseat.planner.domain.repositories;

import com.rocketseat.planner.domain.model.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository <Trip, UUID> {

}
