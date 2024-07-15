package com.rocketseat.planner.service;

import com.rocketseat.planner.dto.participant.ParticipantCreateResponse;
import com.rocketseat.planner.dto.participant.ParticipantData;
import com.rocketseat.planner.exceptions.ParticipantsNotFoundException;
import com.rocketseat.planner.domain.model.participant.Participant;
import com.rocketseat.planner.domain.model.trip.Trip;
import com.rocketseat.planner.domain.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.participantRepository.saveAll(participants);

    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant participant = new Participant(email, trip);
        this.participantRepository.save(participant);
        return new ParticipantCreateResponse(participant.getId());

    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {
    }

    public void triggerConfirmationEmailToParticipant(String email) {
    }

    public List<ParticipantData> getAllParticipants(UUID tripId) {
        List<Participant> participantList = this.participantRepository.findByTripId(tripId);
        if (!participantList.isEmpty()) {
            return participantList.stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();

        } else throw new ParticipantsNotFoundException();

    }
}
