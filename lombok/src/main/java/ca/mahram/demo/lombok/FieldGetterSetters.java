package ca.mahram.demo.lombok;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class FieldGetterSetters {
    @Getter @Setter private int     integer;
    @Getter private         float   floater;
    @Setter private         boolean bool;
    private                 String  string;

    private final Random random = new Random ();

    public float changeFloater () {
        floater = random.nextFloat ();
        return floater;
    }

    public boolean isBool () {
        return bool;
    }
}
