package app.repositories;

import app.entities.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Repository
//@RepositoryRestResource(exported = false)
public interface GameRepository extends CrudRepository<Game, Long> {

    Game findGameByNameLikeIgnoreCase(String name);

    List<Game> findDistinctByMechanicsValue(String mechanic);
}
