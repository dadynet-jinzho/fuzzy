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
public class Host extends Component {
    @GraphId Long id;
    private String ip;

    @Relationship(type="BELONGTO", direction = Relationship.OUTGOING) private Site site;
    @Relationship(type="BELONGTO", direction = Relationship.INCOMING) private List<Vm> vms;
    @Relationship(type="RESIDENT_IN", direction = Relationship.INCOMING) private Rack rack;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Vm> getVms() {
        return vms;
    }

    public void setVms(List<Vm> vms) {
        this.vms = vms;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public void belongTo(Site site){
        this.site = site;
    }
    public void contains(Vm vm){
        if(vms == null){
            vms = new ArrayList<>();
        }
        else {
            vms.add(vm);
        }
    }
    public void residents(Rack rack){
        this.rack = rack;
    }
}
