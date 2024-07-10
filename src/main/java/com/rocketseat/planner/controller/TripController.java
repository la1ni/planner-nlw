package com.rocketseat.planner.controller;

import com.rocketseat.planner.model.participant.*;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.model.trip.TripCreateResponse;
import com.rocketseat.planner.model.trip.TripRequestPayload;
import com.rocketseat.planner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

        participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> findAllParticipants (@PathVariable UUID id){
        List<ParticipantData> participants = this.participantService.getAllParticipants(id);
        return ResponseEntity.ok(participants);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id){
        Optional<Trip> trip = this.tripRepository.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()){
            Trip rawTrip = trip.get();
            rawTrip.setDestination(payload.destination());
            rawTrip.setEndsAt(LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setStartsAt(LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));

            this.tripRepository.save(rawTrip);

            return ResponseEntity.ok(rawTrip);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()){
            Trip rawTrip = trip.get();

            rawTrip.setIsConfirmed(true);
            this.participantService.triggerConfirmationEmailToParticipants(id);
            this.tripRepository.save(rawTrip);

            return ResponseEntity.ok(rawTrip);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){

        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            Trip rawTrip = trip.get();
            ParticipantCreateResponse participantResponse = this.participantService.registerParticipantToEvent(payload.email(), rawTrip);

            if (rawTrip.getIsConfirmed()){
                participantService.triggerConfirmationEmailToParticipant(payload.email());
            }
            return ResponseEntity.ok(participantResponse);
        }
        return ResponseEntity.notFound().build();
    }


}
