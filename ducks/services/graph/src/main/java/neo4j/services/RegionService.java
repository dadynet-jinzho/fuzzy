package neo4j.services;

import neo4j.domain.*;
import neo4j.repositories.DmsRepository;
import neo4j.repositories.MovieRepository;
import neo4j.repositories.RegionRepository;
import neo4j.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RegionService {

    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DmsRepository dmsRepository;

    public void save(Region region){regionRepository.save(region);}

    public void createOrUpdate(String resourceId, BaseMessage message){
        UpdateOrCreateMessage updateOrCreateMessage;
        if(message instanceof UpdateOrCreateMessage){
            updateOrCreateMessage = (UpdateOrCreateMessage) message;

        }
        else {
            System.err.println("error in region createOrUpdate");
            return;
        }
        Region region = regionRepository.findByResourceId(resourceId);
        if(region != null){
            region.setName(updateOrCreateMessage.getName());
            region.setType(updateOrCreateMessage.getType());
            regionRepository.save(region);
        }
        else {
            region = new Region();
            region.setName(updateOrCreateMessage.getName());
            region.setType(updateOrCreateMessage.getType());
            region.setResourceId(resourceId);
            Dms dms = dmsRepository.findByResourceId(Helper.getParentResourceId("region", message));
            region.belongTo(dms);
            regionRepository.save(region);
        }
    }
}