package com.rocketseat.planner.participant.service;

import com.rocketseat.planner.participant.record.ParticipantData;
import com.rocketseat.planner.participant.entity.Participant;
import com.rocketseat.planner.participant.repository.ParticipantRepository;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.trip.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository repository;

    public void registerPaticipantToEvent(List<String> participantToInvate, Trip trip) {
        List<Participant> participants = participantToInvate.stream().map(email -> new Participant(email, trip)).toList();

        this.repository.saveAll(participants);

        System.out.println("Participants ID: " + participants.get(0).getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        this.repository.save(newParticipant);
        return new ParticipantCreateResponse(newParticipant.getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {
    }


    public void triggerConfirmationEmailToParticipant(String email) {
    }

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return this.repository.findByTripId(tripId).stream()
                .map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }
}