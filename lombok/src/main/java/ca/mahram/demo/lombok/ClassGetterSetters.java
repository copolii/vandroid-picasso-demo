package ca.mahram.demo.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassGetterSetters {
    private int intValue;
    private float someFloat;
    private boolean boolValue;
    private String someString;
}
