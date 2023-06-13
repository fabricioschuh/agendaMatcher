package com.example.agenda.participant.controller;

import com.example.agenda.participant.model.Participant;
import com.example.agenda.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/participant")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    public ParticipantController(ParticipantRepository participantRepository) {
        super();
        this.participantRepository = participantRepository;
    }

    //Add new Participant
    @PostMapping
    public ResponseEntity<Participant> save(@RequestBody Participant participant) {
        participantRepository.save(participant);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    //Retrieve all users
    @GetMapping
    public ResponseEntity<List<Participant>> getAll() {
        List<Participant> Participants = new ArrayList<>();
        Participants = participantRepository.findAll();
        return new ResponseEntity<>(Participants, HttpStatus.OK);
    }

    //Retrieve a desired user
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Participant>> getCustomerById(@PathVariable Long id) {
        Optional<Participant> users;
        try {
            users = participantRepository.findById(id);
            return new ResponseEntity<Optional<Participant>>(users, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Participant>>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete a desired users
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Participant>> deleteById(@PathVariable Long id) {
        try {
            participantRepository.deleteById(id);
            return new ResponseEntity<Optional<Participant>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Participant>>(HttpStatus.NOT_FOUND);
        }
    }

    //Update a desired user
    @PutMapping(path = "/{id}")
    public ResponseEntity<Participant> update(@PathVariable Long id, @RequestBody Participant newParticipant) {
        return participantRepository.findById(id)
                .map(Participant -> {
                    Participant.setName(newParticipant.getName());
                    Participant updatedParticipant = participantRepository.save(Participant);
                    return ResponseEntity.ok().body(updatedParticipant);
                }).orElse(ResponseEntity.notFound().build());
    }

    //TODO
    //DeleteAll

}
