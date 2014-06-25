package ca.mahram.demo.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 Created by mahram.foadi on 6/24/2014.
 */
@RequiredArgsConstructor @AllArgsConstructor @Getter
public class WowMuchArg {
    private          int     integer;
    private final    float   floater;
    private          double  two;
    private final    boolean bool;
    @NonNull private String  string;
}
