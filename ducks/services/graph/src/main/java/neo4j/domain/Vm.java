package neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by jinzhou on 6/28/16.
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Vm extends Component {
    @GraphId Long id;
    private String ip;

    @Relationship(type="BELONGTO", direction = Relationship.OUTGOING) private Site site;
    @Relationship(type="BELONGTO", direction = Relationship.INCOMING) private List<Vm> vms;


}
