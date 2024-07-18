package com.rocketseat.planner.participant.controller;

import com.rocketseat.planner.participant.record.ParticipantRequestPayload;
import com.rocketseat.planner.participant.entity.Participant;
import com.rocketseat.planner.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {


    @Autowired
    private ParticipantRepository repository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.repository.findById(id);

        if (participant.isPresent()) {
            Participant rawParticant = participant.get();
            rawParticant.setIsConfirmed(true);
            rawParticant.setName(payload.name());

            this.repository.save(rawParticant);

            return ResponseEntity.ok(rawParticant);
        }

        return ResponseEntity.notFound().build();
    }
}
