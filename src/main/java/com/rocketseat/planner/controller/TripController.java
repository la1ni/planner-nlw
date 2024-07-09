package com.rocketseat.planner.controller;

import com.rocketseat.planner.model.participant.ParticipantService;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.model.trip.TripCreateResponse;
import com.rocketseat.planner.model.trip.TripRequestPayload;
import com.rocketseat.planner.repository.TripRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip (@RequestBody TripRequestPayload payload){
        Trip newTrip = new Trip(payload);

        this.tripRepository.save(newTrip);

        participantService.registerParticipantToEvent(payload.emails_to_invite(), newTrip.getId());

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id){
        Optional<Trip> trip = this.tripRepository.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
