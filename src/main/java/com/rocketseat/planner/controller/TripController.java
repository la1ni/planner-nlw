package com.rocketseat.planner.controller;

import com.rocketseat.planner.dto.activity.ActivityData;
import com.rocketseat.planner.dto.activity.ActivityRequestPayload;
import com.rocketseat.planner.dto.activity.ActivityResponse;
import com.rocketseat.planner.dto.link.LinkData;
import com.rocketseat.planner.dto.link.LinkRequestPayload;
import com.rocketseat.planner.dto.link.LinkResponse;
import com.rocketseat.planner.dto.participant.ParticipantCreateResponse;
import com.rocketseat.planner.dto.participant.ParticipantData;
import com.rocketseat.planner.dto.participant.ParticipantRequestPayload;
import com.rocketseat.planner.dto.trip.TripCreateResponse;
import com.rocketseat.planner.dto.trip.TripRequestPayload;
import com.rocketseat.planner.service.ActivityService;
import com.rocketseat.planner.domain.model.trip.Trip;
import com.rocketseat.planner.domain.repositories.TripRepository;
import com.rocketseat.planner.service.LinkService;
import com.rocketseat.planner.service.ParticipantService;
import com.rocketseat.planner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TripService tripService;

    // Trip

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip (@RequestBody TripRequestPayload payload){
        return ResponseEntity.ok(this.tripService.createTrip(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id){
        return ResponseEntity.ok(this.tripService.getTrip(id));
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.confirmTrip(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        return ResponseEntity.ok(tripService.updateTrip(id, payload));
    }

    // Participants

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){

            return ResponseEntity.ok(tripService.inviteParticipant(id, payload));
        }


    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> findAllParticipants (@PathVariable UUID id){
        List<ParticipantData> participants = this.participantService.getAllParticipants(id);
        return ResponseEntity.ok(participants);
    }

    // Activity

    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload){
            return ResponseEntity.ok(tripService.registerActivity(id, payload));
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> findAllActivities (@PathVariable UUID id){

        List<ActivityData> activities = this.activityService.getAllActivities(id);
        return ResponseEntity.ok(activities);
    }

    // Links

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload){
        return ResponseEntity.ok(tripService.registerLink(id, payload));

    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> findAllLinks(@PathVariable UUID id){
        return ResponseEntity.ok(linkService.findAllLinks(id));
    }


}
