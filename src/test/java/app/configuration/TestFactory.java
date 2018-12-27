package app.configuration;

import app.entities.Game;
import app.entities.Mechanic;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class TestFactory {
    public static Game buildGloomHavenGame() {
        List<Mechanic> expectedMechanics = asList("Action / Movement Programming",
                "Co-operative Play", "Grid Movement", "Hand Management", "Modular Board",
                "Role Playing", "Simultaneous Action Selection", "Storytelling", "Variable Player Powers")
                .stream()
                .map(Mechanic::new)
                .collect(Collectors.toList());

        return Game.builder()
                .bggUrl("https://boardgamegeek.com/boardgame/174430/gloomhaven")
                .rank(1L)
                .name("Gloomhaven")
                .mechanics(expectedMechanics)
                .categories("Adventure, Exploration, Fantasy, Fighting, Miniatures")
                .build();
    }
}
