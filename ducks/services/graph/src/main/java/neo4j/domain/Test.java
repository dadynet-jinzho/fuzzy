package neo4j.domain;

import org.springframework.stereotype.Component;


/**
 * Created by jinzhou on 6/28/16.
 */
public class Test {
    public static void main(String[] args) {
        String test = "dms_jiangsu_hexi";
        System.out.println(test.substring(0,test.lastIndexOf("_")));
    }
}
