package app.repositories;

import app.entities.Game;
import app.entities.Mechanic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;


    @Test
    public void findGamesWithASingleGivenMechanic() {
        Game game1 = Game.builder()
                .mechanics(asList(new Mechanic("a-mechanic")))
                .build();

        Game game2 = Game.builder()
                .mechanics(asList(new Mechanic("b-mechanic")))
                .build();

        Game game3 = Game.builder()
                .mechanics(asList(new Mechanic("a-mechanic")))
                .build();

        gameRepository.save(asList(game1, game2, game3));

        List<Game> actualGames = gameRepository.findDistinctByMechanicsValueIn(asList("a-mechanic"));

        assertThat(actualGames.size(), equalTo(2));

    }

    @Test
    public void findGamesWithMultipleGivenMechanics() {
        Game game1 = Game.builder()
                .mechanics(asList(new Mechanic("a-mechanic")))
                .build();

        Game game2 = Game.builder()
                .mechanics(asList(new Mechanic("b-mechanic")))
                .build();

        Game game3 = Game.builder()
                .mechanics(asList(new Mechanic("a-mechanic")))
                .build();

        gameRepository.save(asList(game1, game2, game3));

        List<Game> actualGames = gameRepository.findDistinctByMechanicsValueIn(asList("a-mechanic", "b-mechanic"));

        assertThat(actualGames.size(), equalTo(3));

    }
}