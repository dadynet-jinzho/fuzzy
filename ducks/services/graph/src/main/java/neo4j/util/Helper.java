package neo4j.util;

import com.fasterxml.jackson.databind.deser.Deserializers;
import neo4j.domain.BaseMessage;

/**
 * Created by jinzhou on 6/28/16.
 */
public class Helper {
    public static String getResourceId(String type, BaseMessage baseMessage){
       return getResourceId(type, baseMessage,null);
    }
    public static String getResourceId(String type,BaseMessage baseMessage, String prefix){
        String resourceId;
        switch (type){
            case "dms":
                resourceId = "dms";
                break;
            case "region":
                resourceId = prefix + "_" + baseMessage.getName();
                break;
            case "site":
                resourceId = prefix + "_" + baseMessage.getName();
                break;
            case "component":
                if(baseMessage.getName() != null){
                    resourceId = prefix + "_" + baseMessage.getType() + "_" + baseMessage.getName();
                }
                else{
                    resourceId = prefix + "_" + baseMessage.getType() + "_" + baseMessage.getIp();
                }
                break;
            default:
                resourceId = "UNKNOWN";
        }
        return resourceId;
    }
    public static String getParentResourceId(String type, BaseMessage baseMessage){
        return getParentResourceId(type,baseMessage,null);
    }
    public static String getParentResourceId(String type, BaseMessage baseMessage, String resourceId){
        String parentResourceId;
        switch (type){
            case "region":
                parentResourceId = "dms";
                break;
            case "site":
                parentResourceId = resourceId.substring(0,resourceId.lastIndexOf("_"));
                break;
            default:
                parentResourceId = "UNKNOWN";
        }
        return parentResourceId;
    }
}
