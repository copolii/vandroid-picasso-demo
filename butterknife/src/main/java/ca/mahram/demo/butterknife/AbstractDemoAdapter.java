package ca.mahram.demo.butterknife;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 Created by mahramf on 20/06/14.
 */
public abstract class AbstractDemoAdapter
  extends BaseAdapter {
    @Override public int getCount () {
        return DemoConstants.IMAGE_URIS.length;
    }

    @Override public Uri getItem (final int position) {
        return DemoConstants.getUri (position);
    }

    @Override public long getItemId (final int position) {
        return position;
    }

    @Override public View getView (final int position, final View convertView, final ViewGroup parent) {
        final View v = null == convertView ? newView (parent) : convertView;
        bindView (v, getItem (position));
        return v;
    }

    protected abstract void bindView (final View v, final Uri item);

    protected abstract View newView (final ViewGroup parent);
}
