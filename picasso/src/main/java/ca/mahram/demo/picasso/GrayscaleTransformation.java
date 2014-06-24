package ca.mahram.demo.picasso;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 Created by mahra_000 on 6/19/2014.
 */
public class GrayscaleTransformation
  implements Transformation {
    @Override public Bitmap transform (final Bitmap source) {
        final ColorMatrix zeroSaturation = new ColorMatrix ();
        zeroSaturation.setSaturation (0f);
        final Paint paint = new Paint ();
        paint.setColorFilter (new ColorMatrixColorFilter (zeroSaturation));

        final Bitmap out = Bitmap.createBitmap (source.getWidth (), source.getHeight (), Bitmap.Config.ARGB_8888);
        new Canvas (out).drawBitmap (source, 0, 0, paint);
        source.recycle ();

        return out;
    }

    @Override public String key () {
        return getClass ().getCanonicalName ();
    }
}
