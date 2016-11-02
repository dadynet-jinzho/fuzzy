package neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by jinzhou on 6/28/16.
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Rack implements Component {
    @GraphId Long id;
    private String ip;
    private String resourceId;



}
