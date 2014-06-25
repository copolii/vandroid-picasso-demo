package mahram.ca.demo.gson.enums;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public enum Tier {
    REGULAR,
    PREMIUM,
    UPGRADED,
    SUPREME;

    public static class TypeAdapter
      implements JsonSerializer<Tier>, JsonDeserializer<Tier> {

        @Override public Tier deserialize (final JsonElement json,
                                           final Type typeOfT,
                                           final JsonDeserializationContext context)
          throws JsonParseException {
            return Tier.values ()[json.getAsInt ()];
        }

        @Override public JsonElement serialize (final Tier src,
                                                final Type typeOfSrc,
                                                final JsonSerializationContext context) {
            return new JsonPrimitive (src.ordinal ());
        }
    }
}
