package app.repositories;

import app.entities.Mechanic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Set;

@RepositoryRestResource
@Repository
public interface MechanicRepository extends CrudRepository<Mechanic, Long> {

}
