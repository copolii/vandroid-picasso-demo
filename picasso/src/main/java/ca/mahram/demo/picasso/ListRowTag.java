package ca.mahram.demo.picasso;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 Created by mahra_000 on 6/20/2014.
 */
public class ListRowTag {
    public ImageView image;
    public TextView  title;

    public ListRowTag (final View view) {
        image = (ImageView) view.findViewById (android.R.id.icon);
        title = (TextView) view.findViewById (android.R.id.title);
    }
}
