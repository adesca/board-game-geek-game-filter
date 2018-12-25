package app.configuration;

import app.entities.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameRepositoryPopulatorFactoryBeanTest {

    GameResourceReader subject = new GameResourceReader();

    @Test
    public void GameResourceReader_readFrom_convertsALineIntoAnObject() throws Exception {
       Game expectedGame = TestFactory.buildGloomHavenGame();

        Resource singleGameResource = new ClassPathResource("single-game.csv");
        List<Game> actualGame = (List<Game>) subject.readFrom(singleGameResource, Game.class.getClassLoader());

        assertEquals(expectedGame, actualGame.get(0));
    }

    @Test
    public void GameResourceReader_readFrom_convertsAFileWithMultipleLines_intoAListOfObjects() throws Exception {
        Resource severalGamesResource = new ClassPathResource("several-games.csv");
        List<Game> actualGames = (List<Game>) subject.readFrom(severalGamesResource, Game.class.getClassLoader());

        assertEquals(actualGames.size(), 5);
    }

}