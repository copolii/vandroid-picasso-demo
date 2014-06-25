package mahram.ca.demo.gson;

import org.json.JSONObject;
import org.junit.Test;

import mahram.ca.demo.gson.base.SimpleGsonTestCase;
import mahram.ca.demo.gson.enums.Tier;
import mahram.ca.demo.gson.model.PlainOldJavaObject;

import static junit.framework.TestCase.assertEquals;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class POJOTest
  extends SimpleGsonTestCase {

    @Test public void pojoSerializeTest () {
        final long now = System.currentTimeMillis ();
        final PlainOldJavaObject pojo = new PlainOldJavaObject (12, "pojo", now, Tier.PREMIUM);

        final JSONObject json = new JSONObject (gson.toJson (pojo));
        assertEquals (12, json.getInt ("count"));
        assertEquals ("pojo", json.getString ("name"));
        assertEquals (now, json.getLong ("timestamp"));
        assertEquals (Tier.PREMIUM.name (), json.getString ("tier"));
    }

    @Test public void pojoDeserializeTest () {
        final String jsonStr =
          "{\"count\":7, \"name\":\"Jean ValJean\", \"timestamp\":1403657386, \"tier\":\"UPGRADED\"}";

        final PlainOldJavaObject pojo = gson.fromJson (jsonStr, PlainOldJavaObject.class);

        assertEquals (7, pojo.getCount ());
        assertEquals ("Jean ValJean", pojo.getName ());
        assertEquals (1403657386, pojo.getTimestamp ());
        assertEquals (Tier.UPGRADED, pojo.getTier ());

        final String jsonWithLnBreaks = "{\n" +
                                        "    \"count\": 354,\n" +
                                        "    \"name\": \"Cosette Tholomyès\",\n" +
                                        "    \"timestamp\": 1403657633,\n" +
                                        "    \"tier\":\"REGULAR\"" +
                                        "}";
        final PlainOldJavaObject mojo = gson.fromJson (jsonWithLnBreaks, PlainOldJavaObject.class);

        assertEquals (354, mojo.getCount ());
        assertEquals ("Cosette Tholomyès", mojo.getName ());
        assertEquals (1403657633, mojo.getTimestamp ());
        assertEquals (Tier.REGULAR, mojo.getTier ());
    }
}
