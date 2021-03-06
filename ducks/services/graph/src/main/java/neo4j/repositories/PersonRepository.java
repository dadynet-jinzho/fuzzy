package neo4j.repositories;

import neo4j.domain.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pdtyreus
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {
    
}