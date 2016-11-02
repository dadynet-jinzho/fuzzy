package neo4j.services;

import neo4j.domain.*;
import neo4j.repositories.*;
import neo4j.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComponentService {

    @Autowired
    SiteRepository siteRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    HostRepository hostRepository;
    @Autowired
    SwitchRepository switchRepository;
    @Autowired
    RackRepository rackRepository;

    public void save(Component component){
        if(component instanceof Host){
            hostRepository.save((Host)component);
        }
        else if(component instanceof Switch){
            switchRepository.save((Switch)component);
        }
    }

    public void createOrUpdate(String resourceId, BaseMessage message){
        UpdateOrCreateMessage updateOrCreateMessage;
        if(message instanceof UpdateOrCreateMessage){
            updateOrCreateMessage = (UpdateOrCreateMessage) message;

        }
        else {
            System.err.println("error in site createOrUpdate");
            return;
        }
        String type = updateOrCreateMessage.getType();
        Component component;
        switch (type){
            case "host":
                component = hostRepository.findByResourceId(resourceId);
                if(component != null){
                    Host host = (Host)component;
                    host.setIp(updateOrCreateMessage.getIp());
                    hostRepository.save(host);
                }
                else{
                    Host host = new Host();
                    host.setIp(updateOrCreateMessage.getIp());
                    host.setResourceId(resourceId);
                    host.setType(updateOrCreateMessage.getType());
                    Site site = siteRepository.findByResourceId(Helper.getParentResourceId("component",updateOrCreateMessage,resourceId));
                    if(site == null){
                        site = new Site();
                        site.setResourceId(resourceId.substring(0,resourceId.indexOf("_host_")));
                        siteRepository.save(site);
                    }
                    host.belongTo(site);
                    hostRepository.save(host);
                }
                break;
            case "rack":
                component = rackRepository.findByResourceId(resourceId);
                break;
            case "switch":
                component = switchRepository.findByResourceId(resourceId);
                break;
            default:
                System.err.println("should not be here");

        }

    }
}