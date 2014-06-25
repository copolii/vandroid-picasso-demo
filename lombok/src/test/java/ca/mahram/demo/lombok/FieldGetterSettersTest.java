package ca.mahram.demo.lombok;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class FieldGetterSettersTest {
    @Test public void ensureHasGettersAndSetters () {
        final FieldGetterSetters test = new FieldGetterSetters ();
        test.setInteger (33);
        assertEquals (33, test.getInteger ());
        assertEquals (test.changeFloater (), test.getFloater ());
        test.setBool (true);
        assertTrue (test.isBool ());
        test.setBool (false);
        assertFalse (test.isBool ());
    }
}
