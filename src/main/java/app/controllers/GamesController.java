package app.controllers;

import app.entities.Mechanic;
import app.repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GamesController {
    @Autowired
    MechanicRepository mechanicRepository;


    @GetMapping("/api/mechanics/all")
    public Set<Mechanic> getAllMechanics() {
        return StreamSupport.stream(mechanicRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(HashSet::new));

    }
}
