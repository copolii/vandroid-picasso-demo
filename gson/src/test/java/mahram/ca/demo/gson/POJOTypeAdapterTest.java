package mahram.ca.demo.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import mahram.ca.demo.gson.base.AbstractGsonTestCase;
import mahram.ca.demo.gson.enums.Tier;
import mahram.ca.demo.gson.model.PlainOldJavaObject;

import static junit.framework.TestCase.assertEquals;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public class POJOTypeAdapterTest
  extends AbstractGsonTestCase {

    @Override protected void configureGsonBuilder (final GsonBuilder builder) {
        builder.registerTypeAdapter (Tier.class, new Tier.TypeAdapter ());
        builder.registerTypeAdapter (boolean.class, new TypeAdapter<Boolean> () {
            @Override public void write (final JsonWriter out, final Boolean value) throws IOException {
                final int val = null == value || !value ? 0 : 1;
                out.value (val);
            }

            @Override public Boolean read (final JsonReader in) throws IOException {
                return in.nextInt () != 0;
            }
        });
    }

    @Test public void pojoSerializeTest () {
        final long now = System.currentTimeMillis ();
        final PlainOldJavaObject pojo = new PlainOldJavaObject (12, "pojo", now, Tier.PREMIUM);

        final JSONObject json = new JSONObject (gson.toJson (pojo));
        assertEquals (12, json.getInt ("count"));
        assertEquals ("pojo", json.getString ("name"));
        assertEquals (now, json.getLong ("timestamp"));
        assertEquals (Tier.PREMIUM.ordinal (), json.getInt ("tier"));
    }

    @Test public void pojoDeserializeTest () {
        final String jsonStr =
          "{\"count\":7, \"name\":\"Jean ValJean\", \"timestamp\":1403657386, \"tier\":2}";

        final PlainOldJavaObject pojo = gson.fromJson (jsonStr, PlainOldJavaObject.class);

        assertEquals (7, pojo.getCount ());
        assertEquals ("Jean ValJean", pojo.getName ());
        assertEquals (1403657386, pojo.getTimestamp ());
        assertEquals (Tier.UPGRADED, pojo.getTier ());

        final String jsonWithLnBreaks = "{\n" +
                                        "    \"count\": 354,\n" +
                                        "    \"name\": \"Cosette Tholomyès\",\n" +
                                        "    \"timestamp\": 1403657633,\n" +
                                        "    \"tier\":0" +
                                        "}";
        final PlainOldJavaObject mojo = gson.fromJson (jsonWithLnBreaks, PlainOldJavaObject.class);

        assertEquals (354, mojo.getCount ());
        assertEquals ("Cosette Tholomyès", mojo.getName ());
        assertEquals (1403657633, mojo.getTimestamp ());
        assertEquals (Tier.REGULAR, mojo.getTier ());
    }
}
