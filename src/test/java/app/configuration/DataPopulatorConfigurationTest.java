package app.configuration;

import app.entities.Game;
import app.repositories.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest(
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {DataPopulatorConfiguration.class})
)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class DataPopulatorConfigurationTest {

    @Autowired
    GameRepository gameRepository;

    @Test
    public void loadsInitialDatabaseData() {
        assertEquals(5, gameRepository.count());
        Game expectedGame = TestFactory.buildGloomHavenGame();

        Game actualGame = gameRepository.findGameByNameLikeIgnoreCase("gloomhaven");
        assertThat(actualGame).isEqualToIgnoringGivenFields(expectedGame,
                "id", "mechanics");

        assertEquals(actualGame.getMechanics().size(), expectedGame.getMechanics().size());

        boolean isEveryMechanicInTheOtherList = expectedGame.getMechanics().stream()
                .allMatch(mechanic -> {
                    return actualGame.getMechanics().stream().anyMatch(mechanic1 -> {
                        return mechanic.equals(mechanic1);
                    });
                });

        assertTrue(isEveryMechanicInTheOtherList);

    }
}