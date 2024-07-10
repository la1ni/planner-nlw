package com.rocketseat.planner.model.participant;

import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.participantRepository.saveAll(participants);
        System.out.println(participants.get(0).getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant participant = new Participant(email, trip);
        this.participantRepository.save(participant);
        return new ParticipantCreateResponse(participant.getId());

    }

    public void triggerConfirmationEmailToParticipants(UUID tripId){}

    public void triggerConfirmationEmailToParticipant(String email){}

    public List<ParticipantData> getAllParticipants(UUID tripId){
        List<Participant> participantList = this.participantRepository.findByTripId(tripId);
        return  participantList.stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }

}
