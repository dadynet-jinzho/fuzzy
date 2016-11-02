package neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinzhou on 6/28/16.
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Dms {
    @GraphId Long id;
    private String name;
    private String resourceId;
    public Dms(){}

    @Relationship(type="BELONGTO", direction = Relationship.INCOMING) private List<Region> regions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void contains(Region region){
        if(regions == null){
            regions = new ArrayList<>();
        }
        regions.add(region);
    }
}
