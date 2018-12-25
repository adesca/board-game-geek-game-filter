package app.configuration;

import app.entities.Game;

import java.util.List;

import static java.util.Arrays.asList;

public class TestFactory {
    public static Game buildGloomHavenGame() {
        List<String> expectedMechanics = asList("Action / Movement Programming",
                "Co-operative Play", "Grid Movement", "Hand Management", "Modular Board",
                "Role Playing", "Simultaneous Action Selection", "Storytelling", "Variable Player Powers");

        return Game.builder()
                .bggUrl("https://boardgamegeek.com/boardgame/174430/gloomhaven")
                .rank(1L)
                .name("Gloomhaven")
                .mechanics(expectedMechanics)
                .build();
    }
}
