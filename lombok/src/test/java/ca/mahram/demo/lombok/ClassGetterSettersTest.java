package ca.mahram.demo.lombok;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class ClassGetterSettersTest {

    @Test public void ensureHasGettersAndSetters () {
        final ClassGetterSetters test = new ClassGetterSetters ();
        test.setIntValue (33);
        test.setBoolValue (true);
        test.setSomeString ("Yo dawg, I heard you like strings!");
        test.setSomeFloat (334.2345f);

        assertEquals (33, test.getIntValue ());
        assertTrue (test.isBoolValue ());
        assertEquals ("Yo dawg, I heard you like strings!", test.getSomeString ());
        assertEquals (334.2345f, test.getSomeFloat ());
    }

    @Test public void ensureOverridesToString () {
        final ClassGetterSetters test = new ClassGetterSetters ();
        test.setIntValue (33);
        test.setBoolValue (true);
        test.setSomeString ("Yo dawg, I heard you like strings!");
        test.setSomeFloat (334.2345f);

        // default toString prints something like: ca.mahram.demo.lombok.ClassGetterSetters@5cb0d902
        assertFalse (test.toString ().startsWith ("ca.mahram.demo.lombok.ClassGetterSetters@"));
    }

    @Test public void ensureEquality () {
        final ClassGetterSetters rhs = new ClassGetterSetters ();
        rhs.setIntValue (33);
        rhs.setBoolValue (true);
        rhs.setSomeString ("Yo dawg, I heard you like strings!");
        rhs.setSomeFloat (334.2345f);

        final ClassGetterSetters same = rhs;

        final ClassGetterSetters lhs = new ClassGetterSetters ();
        lhs.setIntValue (33);
        lhs.setBoolValue (true);
        lhs.setSomeString ("Yo dawg, I heard you like strings!");
        lhs.setSomeFloat (334.2345f);

        // check reference equality
        assertEquals (rhs, same);
        // check value equality
        assertEquals (rhs, same);
        assertEquals (lhs, same);
    }

    @Test public void CheckInequality () {
        final ClassGetterSetters rhs = new ClassGetterSetters ();
        rhs.setIntValue (33);
        rhs.setBoolValue (true);
        rhs.setSomeString ("Yo dawg, I heard you like strings!");
        rhs.setSomeFloat (334.2345f);

        final ClassGetterSetters lhs = new ClassGetterSetters ();
        lhs.setIntValue (77658);
        lhs.setBoolValue (true);
        lhs.setSomeString ("Yo dawg, I heard you like strings!");
        lhs.setSomeFloat (334.2345f);

        // should not be equal
        assertFalse (rhs.equals (lhs));
        lhs.setIntValue (rhs.getIntValue ());
        // should be equal now
        assertEquals (rhs, lhs);

        final boolean bool = rhs.isBoolValue ();

        lhs.setBoolValue (!bool);
        assertFalse (rhs.equals (lhs));
        lhs.setBoolValue (bool);
        assertEquals (rhs, lhs);

        final String rhsString = rhs.getSomeString ();
        lhs.setSomeString (null);
        assertFalse (rhs.equals (lhs));
        lhs.setSomeString ("not " + rhsString);
        assertFalse (rhs.equals (lhs));
        lhs.setSomeString ("bob");
        assertFalse (rhs.equals (lhs));
        lhs.setSomeString ("");
        assertFalse (rhs.equals (lhs));
        lhs.setSomeString (rhsString);
        assertEquals (rhs, lhs);

        final float rhsFloat = rhs.getSomeFloat ();
        lhs.setSomeFloat (rhsFloat * 1.1234f);
        assertFalse (rhs.equals (lhs));
        lhs.setSomeFloat (0f);
        assertFalse (rhs.equals (lhs));
        lhs.setSomeFloat (rhsFloat);
        assertEquals (rhs, lhs);

        // I'm convinecd! Are you?
    }

    @Test public void checkHashCode () {
        final ClassGetterSetters rhs = new ClassGetterSetters ();
        rhs.setIntValue (33);
        rhs.setBoolValue (true);
        rhs.setSomeString ("Yo dawg, I heard you like strings!");
        rhs.setSomeFloat (334.2345f);

        final ClassGetterSetters same = rhs;

        final ClassGetterSetters lhs = new ClassGetterSetters ();
        lhs.setIntValue (33);
        lhs.setBoolValue (true);
        lhs.setSomeString ("Yo dawg, I heard you like strings!");
        lhs.setSomeFloat (334.2345f);

        assertEquals (rhs.hashCode (), lhs.hashCode ());

        // now let's try in a hashset
        final HashSet<ClassGetterSetters> set = new HashSet<ClassGetterSetters> ();
        set.add (rhs);

        assertTrue (set.contains (rhs));
        assertTrue (set.contains (same));
        assertTrue (set.contains (lhs));

        // how about a map?
        final HashMap<ClassGetterSetters, String> map = new HashMap<ClassGetterSetters, String> ();
        map.put (rhs, "rhs");

        assertTrue (map.containsKey (rhs));
        assertTrue (map.containsKey (same));
        assertTrue (map.containsKey (lhs));

        map.put (lhs, "lhs");
        assertTrue (map.containsKey (rhs));
        assertTrue (map.containsKey (same));
        assertTrue (map.containsKey (lhs));

        assertTrue ("lhs".equals (map.get (rhs)));
        assertTrue ("lhs".equals (map.get (same)));
        assertTrue ("lhs".equals (map.get (lhs)));

        map.remove (rhs);

        assertFalse (map.containsKey (rhs));
        assertFalse (map.containsKey (same));
        assertFalse (map.containsKey (lhs));
    }
}
