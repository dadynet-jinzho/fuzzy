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
public class Region {
    @GraphId Long id;

    private String name;
    private String type;
    private String resourceId;
    @Relationship(type="BELONGTO", direction = Relationship.OUTGOING) private Dms dms;
    @Relationship(type="BELONGTO", direction = Relationship.INCOMING) private List<Site> sites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dms getDms() {
        return dms;
    }

    public void setDms(Dms dms) {
        this.dms = dms;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void belongTo(Dms dms){
        this.dms = dms;
    }
    public void contains(Site site){
        if(sites == null){
            sites = new ArrayList<>();
        }
        else {
            sites.add(site);
        }
    }
}
