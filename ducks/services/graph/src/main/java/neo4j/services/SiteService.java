package neo4j.services;

import neo4j.domain.*;
import neo4j.repositories.DmsRepository;
import neo4j.repositories.RegionRepository;
import neo4j.repositories.SiteRepository;
import neo4j.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.netflix.config.DeploymentContext.ContextKey.region;

@Service
@Transactional
public class SiteService {

    @Autowired
    RegionRepository regionRepository;
    @Autowired
    SiteRepository siteRepository;

    public void save(Site site){siteRepository.save(site);}

    public void createOrUpdate(String resourceId, BaseMessage message){
        UpdateOrCreateMessage updateOrCreateMessage;
        if(message instanceof UpdateOrCreateMessage){
            updateOrCreateMessage = (UpdateOrCreateMessage) message;

        }
        else {
            System.err.println("error in site createOrUpdate");
            return;
        }
        Site site = siteRepository.findByResourceId(resourceId);
        if(site != null){
            site.setName(updateOrCreateMessage.getName());
            site.setType(updateOrCreateMessage.getType());
            siteRepository.save(site);
        }
        else {
            site = new Site();
            site.setName(updateOrCreateMessage.getName());
            site.setType(updateOrCreateMessage.getType());
            site.setResourceId(resourceId);
            Region region = regionRepository.findByResourceId(Helper.getParentResourceId("site", message, resourceId));
            site.belongTo(region);
            siteRepository.save(site);
        }
    }
}