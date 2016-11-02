package neo4j.services;

import neo4j.domain.BaseMessage;
import neo4j.domain.Dms;
import neo4j.domain.Movie;
import neo4j.domain.UpdateOrCreateMessage;
import neo4j.repositories.DmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import neo4j.repositories.MovieRepository;

import java.util.*;

@Service
@Transactional
public class DmsService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DmsRepository dmsRepository;

    private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> rels= new ArrayList<Map<String,Object>>();
        int i=0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("title",row.get("movie"),"label","movie"));
            int target=i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> actor = map("title", name,"label","actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source",source,"target",target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String,Object>(2);
        result.put(key1,value1);
        result.put(key2,value2);
        return result;
    }

    public Map<String, Object> graph(int limit) {
        Iterator<Map<String, Object>> result = movieRepository.graph(limit).iterator();
        return toD3Format(result);
    }
    public void save(Movie movie){
        movieRepository.save(movie);
    }
    public void save(Dms dms){dmsRepository.save(dms);}

    public void createOrUpdate(String resourceId, BaseMessage message){
        UpdateOrCreateMessage updateOrCreateMessage;
        if(message instanceof UpdateOrCreateMessage){
            updateOrCreateMessage = (UpdateOrCreateMessage) message;

        }
        else {
            System.err.println("error in dms createOrUpdate");
            return;
        }
        Dms dms = dmsRepository.findByResourceId(resourceId);
        if(dms != null){
            dms.setName(updateOrCreateMessage.getName());
            dmsRepository.save(dms);
        }
        else {
            dms = new Dms();
            dms.setName(updateOrCreateMessage.getName());
            dms.setResourceId(resourceId);
            dmsRepository.save(dms);
        }
    }
}