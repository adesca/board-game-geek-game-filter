package app.controllers;

import app.entities.Mechanic;
import app.repositories.MechanicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GamesControllerTest {

    @Autowired
    GamesController gamesController;

    @Autowired
    MechanicRepository mechanicRepository;

    @Test
    public void getAllMechanics_filtersDuplicateMechanics() {
        Set<Mechanic> actualMechanics = gamesController.getAllMechanics();


        actualMechanics.forEach(mechanic -> {
            assertEquals("Tested mechanic: " + mechanic.getValue(), 1, actualMechanics.stream().filter(mechanic1 -> mechanic.equals(mechanic1))
            .count());
        });


        assertThat((long) actualMechanics.size(), lessThan(mechanicRepository.count()));

    }
}