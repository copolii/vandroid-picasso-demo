package mahram.ca.demo.gson.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 Created by mahram.foadi on 6/24/2014.
 */
public abstract class AbstractGsonTestCase {

    protected final Gson gson;

    protected AbstractGsonTestCase () {
        final GsonBuilder builder = new GsonBuilder ();

        configureGsonBuilder (builder);

        gson = builder.create ();
    }

    protected abstract void configureGsonBuilder (final GsonBuilder builder);
}
