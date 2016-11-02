package neo4j.repositories;

import neo4j.domain.Dms;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jinzhou on 6/28/16.
 */
@Repository
public interface DmsRepository extends GraphRepository<Dms> {
    Dms findByResourceId(@Param("resourceId") String resourceId);
}
