package ca.mahram.demo.lombok;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 Created by mahram.foadi on 6/24/2014.
 */
@NoArgsConstructor
@Getter
public class LookMaNoArgs {
    private int     integer;
    private float   floater;
    private double  two;
    private boolean bool;
    private String  string;

    public LookMaNoArgs (final int anInt, final boolean aBool) {
        integer = anInt;
        bool = aBool;
        string = "";
        floater = 0f;
        two = 2d;
    }
}
