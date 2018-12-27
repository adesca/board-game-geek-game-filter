package app.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(
        name = "fullGame",
        types = { Game.class }
)
public interface FullGameProjection {

    String getName();
    Long getRank();
    String getBggUrl();
    String getCategories();

    List<Mechanic> getMechanics();
}
