package com.rocketseat.planner.service;

import com.rocketseat.planner.dto.activity.ActivityRequestPayload;
import com.rocketseat.planner.dto.activity.ActivityResponse;
import com.rocketseat.planner.dto.link.LinkRequestPayload;
import com.rocketseat.planner.dto.link.LinkResponse;
import com.rocketseat.planner.dto.participant.ParticipantCreateResponse;
import com.rocketseat.planner.dto.participant.ParticipantRequestPayload;
import com.rocketseat.planner.dto.trip.TripCreateResponse;
import com.rocketseat.planner.dto.trip.TripRequestPayload;
import com.rocketseat.planner.exception.ResourceNotFoundException;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    // função auxiliar da classe
    public Trip verifyIfTripExists(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            throw new ResourceNotFoundException("Viagem não encontrada");
        }
    }

    public TripCreateResponse createTrip(TripRequestPayload payload) {
        Trip newTrip = new Trip(payload);
        this.tripRepository.save(newTrip);
        this.participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);
        return new TripCreateResponse(newTrip.getId());
    }

    public Trip getTrip(UUID id) {
        return this.verifyIfTripExists(id);
    }

    public Trip confirmTrip(UUID id){

        Trip trip = this.verifyIfTripExists(id);
        trip.setIsConfirmed(true);
        this.participantService.triggerConfirmationEmailToParticipants(id);
        this.tripRepository.save(trip);
        return trip;
        }

    public Trip updateTrip(UUID id, TripRequestPayload payload) {

        Trip rawTrip = verifyIfTripExists(id);
        rawTrip.setDestination(payload.destination());
        rawTrip.setEndsAt(LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
        rawTrip.setStartsAt(LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
        this.tripRepository.save(rawTrip);
        return rawTrip;
    }

    public ParticipantCreateResponse inviteParticipant(UUID id, ParticipantRequestPayload payload){

        Trip rawTrip = verifyIfTripExists(id);

        ParticipantCreateResponse participantResponse = this.participantService.registerParticipantToEvent(payload.email(), rawTrip);

        if (rawTrip.getIsConfirmed()) {
            participantService.triggerConfirmationEmailToParticipant(payload.email());
        }
        return participantResponse;
    }

    public ActivityResponse registerActivity(UUID id, ActivityRequestPayload payload) {
        Trip rawTrip = verifyIfTripExists(id);
        return this.activityService.createActivity(payload, rawTrip);

    }

    public LinkResponse registerLink(UUID id, LinkRequestPayload payload){
       Trip rawTrip = verifyIfTripExists(id);
       return linkService.createLink(payload, rawTrip);
        }
}



