package neo4j.domain;

/**
 * Created by jinzhou on 6/28/16.
 */
public abstract class Component {
    private String type;
    private String resourceId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
