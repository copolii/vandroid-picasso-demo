package ca.mahram.demo.butterknife;

import android.content.Context;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import java.util.Random;

import ca.mahram.demo.butterknife.BuildConfig;

/**
 Created by mahra_000 on 6/19/2014.
 */
public final class DemoConstants {
    private DemoConstants () {
    } // no instance for you

    private static Picasso picasso;

    private static Random randomizer = new Random ();

    public static final String[] IMAGE_URIS =
      {
        "https://farm4.staticflickr.com/3900/14378892662_b11b11e87a_z.jpg",
        "https://farm3.staticflickr.com/2898/14378897582_b4dd1c1e61_z.jpg",
        "https://farm4.staticflickr.com/3838/14379379364_d7f90a56cc_z.jpg",
        "https://farm6.staticflickr.com/5490/14377014721_770c56885a_z.jpg",
        "https://farm4.staticflickr.com/3769/14082984478_bb7907985a_z.jpg",
        "https://farm3.staticflickr.com/2936/14082989818_aa1dae864a_z.jpg",
        "https://farm8.staticflickr.com/7396/14139922472_07db1ab3c7_z.jpg",
        "https://farm8.staticflickr.com/7338/13956152397_fb59ed6b43_z.jpg",
        "https://farm8.staticflickr.com/7373/13956344039_29fedfc6da_z.jpg",
        "https://farm8.staticflickr.com/7413/14119666386_93895717f1_z.jpg",
        "https://farm8.staticflickr.com/7300/14120758554_39350042e4_z.jpg",
        "https://farm3.staticflickr.com/2908/13933801738_73d25b4953_z.jpg",
        "https://farm8.staticflickr.com/7324/14119762755_d59356a6f8_z.jpg",
        "https://farm6.staticflickr.com/5194/14139833363_c7105930b3_z.jpg",
        "https://farm8.staticflickr.com/7317/13933198069_58847aae26_z.jpg",
        "https://farm6.staticflickr.com/5157/13933169220_98be4abc39_z.jpg",
        "https://farm3.staticflickr.com/2927/14139913653_9a7a03a7ed_z.jpg",
        "https://farm6.staticflickr.com/5344/10167067853_f5e713e030_z.jpg",
        "https://farm4.staticflickr.com/3774/10167122735_1aac1f77c2_z.jpg",
        "https://farm4.staticflickr.com/3700/10165441826_911db6ef08_z.jpg",
        "https://farm3.staticflickr.com/2837/10153432015_15604db25f_z.jpg",
        "https://farm4.staticflickr.com/3719/9559181953_1515c4a3b3_z.jpg",
        "https://farm4.staticflickr.com/3668/9527107763_54a8d230ab_z.jpg",
        "https://farm6.staticflickr.com/5488/9509064282_1a3a80c78a_z.jpg",
        "https://farm4.staticflickr.com/3707/9509079456_57e432a103_z.jpg",
        "https://farm4.staticflickr.com/3795/9506302435_0396799476_z.jpg",
        "https://farm4.staticflickr.com/3682/9442646614_16c038e92f_z.jpg",
        "https://farm4.staticflickr.com/3791/9288885061_fcfd65e949_z.jpg",
        "https://farm6.staticflickr.com/5451/9288921385_67d5377c87_z.jpg",
        "https://farm4.staticflickr.com/3674/9291726824_5410d2cbe3_z.jpg",
        "https://farm3.staticflickr.com/2876/9091626640_9c8d899b65_z.jpg",
        "https://lh4.googleusercontent.com/-pCSNJqj16y4/U2yDCFLzR1I/AAAAAAAAknw/jo07-88TX5g/w435-h652-no/_DSC1503.jpg",
        "https://lh5.googleusercontent.com/-1CNuae2Sjg4/U2yDjEzsI-I/AAAAAAAAkjs/bp66xSVZS2c/w978-h652-no/_DSC1523.jpg",
        "https://lh4.googleusercontent.com/-dFdy6PsCX4M/Ubbysaboy6I/AAAAAAAAb64/p1-3-pDZY_k/w981-h652-no/_DSC0841.JPG",
        "https://lh3.googleusercontent.com/-EMUolsezUZ0/UbWWhagqiPI/AAAAAAAAVgM/0UTU-d86rR0/w1159-h652-no/IMGP0195.JPG"
      };

    public static Picasso picasso (final Context context) {
        if (null == picasso) {
            picasso = new Picasso.Builder (context)
                        .indicatorsEnabled (BuildConfig.DEBUG)
                        .loggingEnabled (BuildConfig.DEBUG)
                        .build ();
        }

        return picasso;
    }

    public static String randomImageUrl () {
        return IMAGE_URIS[randomizer.nextInt (IMAGE_URIS.length)];
    }

    public static Uri randomImageUri () {
        return Uri.parse (randomImageUrl ());
    }

    public static Uri randomInvalidImageUri () {
        return randomImageUri ().buildUpon ().scheme ("http").authority ("totally.invalid.com").build ();
    }

    public static String randomInvalidImageUrl () {
        return randomInvalidImageUri ().toString ();
    }

    public static String randomValidOrInvalidImageUrl (final float threshold) {
        if (threshold < 0.0f || threshold > 1.0f) {
            throw new IllegalArgumentException ("between 0 and 1, son! 0 to 1.");
        }

        return randomizer.nextFloat () >= threshold ? randomImageUrl () : randomInvalidImageUrl ();
    }

    public static Uri randomValidOrInvalidImageUri (final float threshold) {
        if (threshold < 0.0f || threshold > 1.0f) {
            throw new IllegalArgumentException ("between 0 and 1, son! 0 to 1.");
        }

        return randomizer.nextFloat () >= threshold ? randomImageUri () : randomInvalidImageUri ();
    }

    public static Uri getUri (final int position) {
        return Uri.parse (IMAGE_URIS[position]);
    }
}
