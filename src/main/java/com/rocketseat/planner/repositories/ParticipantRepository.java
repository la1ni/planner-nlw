package com.rocketseat.planner.repositories;


import com.rocketseat.planner.dto.participant.ParticipantCreateResponse;
import com.rocketseat.planner.model.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    List<Participant> findByTripId(UUID tripId);


}
