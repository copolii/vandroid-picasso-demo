package ca.mahram.demo.butterknife;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 Created by mahra_000 on 6/20/2014.
 */
public class ListRowTag {
    @InjectView (android.R.id.icon) public  ImageView image;
    @InjectView (android.R.id.title) public TextView  title;

    public ListRowTag (final View view) {
        ButterKnife.inject (this, view);
    }
}
