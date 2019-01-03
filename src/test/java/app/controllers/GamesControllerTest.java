package app.controllers;

import app.entities.Game;
import app.entities.Mechanic;
import app.repositories.GameRepository;
import app.repositories.MechanicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GamesControllerTest {

    @Autowired
    GamesController gamesController;

    @Autowired
    MechanicRepository mechanicRepository;
    @Autowired
    GameRepository gameRepository;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllMechanics_filtersDuplicateMechanics() {
        Set<Mechanic> actualMechanics = gamesController.getAllMechanics();


        actualMechanics.forEach(mechanic -> {
            assertEquals("Tested mechanic: " + mechanic.getValue(), 1, actualMechanics.stream().filter(mechanic1 -> mechanic.equals(mechanic1))
                    .count());
        });


        assertThat((long) actualMechanics.size(), lessThan(mechanicRepository.count()));

    }

    @Test
    @Transactional
    public void fetchGamesByMechanics_returnsOnlyGamesWithAllMechanics() throws Exception {
        Game gameWithWantedMechanicsAndMore = Game.builder()
                .name("game1")
                .rank(1L)
                .mechanics(
                        asList(new Mechanic("mechanic1"), new Mechanic("mechanic2"),
                                new Mechanic("mechanic3"))
                )
                .build();

        Game gameWithOnlyASubsetOfWantedMechanics = Game.builder()
                .name("game2")
                .rank(2L)
                .mechanics(
                        asList(new Mechanic("mechanic1"), new Mechanic("mechanic3"))
                )
                .build();

        Game gameWithOnlyWantedMechanics = Game.builder()
                .name("game3")
                .rank(3L)
                .mechanics(
                        asList(new Mechanic("mechanic1"), new Mechanic("mechanic2"))
                )
                .build();

        gameRepository.save(asList(gameWithOnlyASubsetOfWantedMechanics, gameWithOnlyWantedMechanics,
                gameWithWantedMechanicsAndMore));


        MvcResult result = mockMvc.perform(get("/api/games")
                .param("mechanics", "mechanic1", "mechanic2"))
                .andExpect(jsonPath("$").isArray())
                .andReturn();


        List actualGames = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(actualGames.size(), 2);
        assertThatThereIsOnlyOneGameWithName(actualGames, "game1");
        assertThatThereIsOnlyOneGameWithName(actualGames, "game3");

    }

    @Test
    @Transactional
    public void fetchGamesByMechanics_sortsGamesByRankAsc() throws Exception {
        Game gameWithWantedMechanicsAndMore = Game.builder()
                .name("game1")
                .rank(2L)
                .mechanics(
                        asList(new Mechanic("mechanic1"), new Mechanic("mechanic2"),
                                new Mechanic("mechanic3"))
                )
                .build();

        Game gameWithOnlyWantedMechanics = Game.builder()
                .rank(1L)
                .name("game3")
                .mechanics(
                        asList(new Mechanic("mechanic1"), new Mechanic("mechanic2"))
                )
                .build();

        gameRepository.save(asList( gameWithOnlyWantedMechanics, gameWithWantedMechanicsAndMore));


        MvcResult result = mockMvc.perform(get("/api/games")
                .param("mechanics", "mechanic1", "mechanic2"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("game3"))
                .andExpect(jsonPath("$[1].name").value("game1"))
                .andReturn();

    }

    private void assertThatThereIsOnlyOneGameWithName(List actualGames, String gameName) {
        long game1Count = actualGames.stream().filter(game -> {
             return ((LinkedHashMap) game).get("name").equals(gameName);
        }).count();

        assertEquals("Assertion failed while looking for " + gameName, 1L, game1Count);
    }
}