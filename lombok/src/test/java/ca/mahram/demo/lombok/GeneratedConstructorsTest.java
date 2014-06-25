package ca.mahram.demo.lombok;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class GeneratedConstructorsTest {

    @Test public void noArgsConstructorTest () {
        final LookMaNoArgs noArgs = new LookMaNoArgs ();
        assertEquals (0, noArgs.getInteger ());
        assertFalse (noArgs.isBool ());
        assertNull (noArgs.getString ());
        assertEquals (0f, noArgs.getFloater ());
        assertEquals (0d, noArgs.getTwo ());

        final LookMaNoArgs args = new LookMaNoArgs (12, true);
        assertEquals (12, args.getInteger ());
        assertTrue (args.isBool ());
        assertEquals ("", args.getString ());
        assertEquals (0f, args.getFloater ());
        assertEquals (2d, args.getTwo ());
    }

    @Test public void allArgConstructorTest () {
        final WowMuchArg allArg = new WowMuchArg (12, 75.34f, 44.547d, true, "bob");
        assertEquals (12, allArg.getInteger ());
        assertEquals (75.34f, allArg.getFloater ());
        assertEquals (44.547d, allArg.getTwo ());
        assertTrue (allArg.isBool ());
        assertEquals ("bob", allArg.getString ());
    }

    @Test public void requiredArgConstructorTest () {
        final WowMuchArg reqArg = new WowMuchArg (75.34f, true, "bob");
        assertEquals (0, reqArg.getInteger ());
        assertEquals (75.34f, reqArg.getFloater ());
        assertEquals (0d, reqArg.getTwo ());
        assertTrue (reqArg.isBool ());
        assertEquals ("bob", reqArg.getString ());
    }

    @Test (expected = NullPointerException.class) public void allArgNonNullNPETest () {
        WowMuchArg allArg = new WowMuchArg (12, 75.34f, 44.547d, true, null);
        // should throw null pointer exception
    }

    @Test (expected = NullPointerException.class) public void requiredArgNonNullNPETest () {
        WowMuchArg reqArg = new WowMuchArg (75.34f, true, null);
        // should throw null pointer exception
    }
}
