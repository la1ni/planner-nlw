package com.rocketseat.planner.controller;

import com.rocketseat.planner.model.participant.Participant;
import com.rocketseat.planner.model.participant.ParticipantRequestPayload;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID tripId, @RequestBody ParticipantRequestPayload payload){
        Optional<Participant> participant = participantRepository.findById(tripId);
        if (participant.isPresent()){
            Participant rawParticipant = participant.get();
            rawParticipant.setIsConfirmed(true);
            rawParticipant.setName(payload.name());
            participantRepository.save(rawParticipant);
            return ResponseEntity.ok(rawParticipant);
        }
        return ResponseEntity.notFound().build();
    }

}
