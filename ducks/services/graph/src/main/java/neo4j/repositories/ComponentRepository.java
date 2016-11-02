package neo4j.repositories;

import neo4j.domain.Site;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jinzhou on 6/28/16.
 */
@Repository
public interface ComponentRepository extends GraphRepository<Site> {
    Site findByResourceId(@Param("resourceId") String resourceId);
}
