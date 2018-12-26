package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ApplicationContractTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void gamesWithSpecifiedMechanicsCanBeFetched() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/games/search/findGamesWithMechanics")
                        .param("mechanics", "Role Playing", "Tile Placement", "Trading")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.games").isArray())
                .andReturn();

    }

    @Test
    public void gamesIncludeMechanicsIntheirJsonRepresentations() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/games/1")
                .param("projection", "fullGame")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mechanics").isArray())
                .andReturn();

    }

    @Test
    public void gamesCanBeSearchedWithOnlyOneMechanic() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/games")
                        .param("mechanic.value", "Role Playing")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.games").isArray())
                .andReturn();

    }
}