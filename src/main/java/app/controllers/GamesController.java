package app.controllers;

import app.entities.Game;
import app.entities.Mechanic;
import app.repositories.GameRepository;
import app.repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GamesController {
    @Autowired
    MechanicRepository mechanicRepository;

    @Autowired
    GameRepository gameRepository;


    @GetMapping("/api/mechanics/all")
    public Set<Mechanic> getAllMechanics() {
        return StreamSupport.stream(mechanicRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(HashSet::new));

    }

    @GetMapping("/api/games")
    public List<Game> getGamesByQuery(@RequestParam("mechanics") List<String> mechanics) {

        return gameRepository.findDistinctByMechanicsValueIn(mechanics)
                .stream().filter(game -> {
                    List<String> mechanicValues = game.getMechanics().stream().map(Mechanic::getValue)
                            .collect(Collectors.toList());

                   return mechanicValues.containsAll(mechanics);
                })
                .sorted(Comparator.comparingLong(Game::getRank))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/games/top")
    public List<Game> getTopGames() {
        return gameRepository.findTop10ByOrderByRankAsc();
    }
}
