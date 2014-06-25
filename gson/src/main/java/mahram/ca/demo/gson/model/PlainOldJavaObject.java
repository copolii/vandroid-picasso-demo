package mahram.ca.demo.gson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mahram.ca.demo.gson.enums.Tier;

/**
 Created by mahram.foadi on 6/24/2014.
 */
@AllArgsConstructor @Getter @Setter
public class PlainOldJavaObject {
    private int    count;
    private String name;
    private long   timestamp;
    private Tier   tier;
}
