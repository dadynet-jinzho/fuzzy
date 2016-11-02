package neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import java.io.IOException;

/**
 * Created by jinzhou on 6/27/16.
 */
@SpringBootApplication(scanBasePackages = {"neo4j"})
@Import(neo4j.Neo4jConfig.class)
@EnableEurekaClient
public class Neo4jApplication{

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Neo4jApplication.class, args);
    }
}
