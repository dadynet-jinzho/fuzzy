package neo4j;

import neo4j.domain.Dms;
import neo4j.domain.Movie;
import neo4j.domain.Region;
import neo4j.domain.UpdateOrCreateMessage;
import neo4j.services.ComponentService;
import neo4j.services.DmsService;
import neo4j.services.RegionService;
import neo4j.services.SiteService;
import neo4j.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jinzhou on 6/28/16.
 */
@RestController("/")
public class Controller {
    @Autowired
    DmsService dmsService;
    @Autowired
    RegionService regionService;
    @Autowired
    SiteService siteService;
    @Autowired
    ComponentService componentService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<UpdateOrCreateMessage> dms(@RequestBody List<UpdateOrCreateMessage> updateOrCreateMessages) {
        for(UpdateOrCreateMessage message : updateOrCreateMessages){
            String resourceId = Helper.getResourceId("dms",message);
            dmsService.createOrUpdate(resourceId,message);
//            Dms dms = new Dms();
//            dms.setName(message.getName());
//            dms.setResourceId(resourceId);
//            Region region = new Region();
//            region.setName("test");
//            dms.contains(region);
//            dmsService.save(dms);
        }
        return updateOrCreateMessages;
    }

    @RequestMapping(value = "/dms", method = RequestMethod.POST)
    public List<UpdateOrCreateMessage> region(@RequestBody List<UpdateOrCreateMessage> updateOrCreateMessages){
        for(UpdateOrCreateMessage message : updateOrCreateMessages){
            String resourceId = Helper.getResourceId("region", message, "dms");
            regionService.createOrUpdate(resourceId,message);
        }
        return updateOrCreateMessages;
    }
    @RequestMapping(value = "/dms/region/{regionName}", method = RequestMethod.POST)
    public List<UpdateOrCreateMessage> site(@PathVariable String regionName, @RequestBody List<UpdateOrCreateMessage> updateOrCreateMessages){
        for(UpdateOrCreateMessage message : updateOrCreateMessages){
            String resourceId = Helper.getResourceId("site", message, "dms_"+regionName);
            siteService.createOrUpdate(resourceId,message);
        }
        return updateOrCreateMessages;
    }

    @RequestMapping(value = "/dms/region/{regionName}/site/{siteName}", method = RequestMethod.POST)
    public List<UpdateOrCreateMessage> component(@PathVariable String regionName,@PathVariable String siteName, @RequestBody List<UpdateOrCreateMessage> updateOrCreateMessages){
        for(UpdateOrCreateMessage message : updateOrCreateMessages){
            String resourceId = Helper.getResourceId("component", message, "dms_"+regionName+"_"+siteName);
            componentService.createOrUpdate(resourceId,message);
        }
        return updateOrCreateMessages;
    }
    @RequestMapping("/test")
    public Movie test(@RequestParam(value = "limit",required = false) Integer limit) {
        Movie movie = new Movie();
        movie.setTitle("movietest");
        dmsService.save(movie);

        return movie;
    }
    @RequestMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return dmsService.graph(limit == null ? 100 : limit);
    }
}
