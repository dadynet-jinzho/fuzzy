package neo4j.domain;

import org.springframework.stereotype.Component;

/**
 * Created by jinzhou on 6/28/16.
 */
@Component
public class BaseMessage {
    private String name;
    private String ip;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
