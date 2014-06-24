package ca.mahram.demo.butterknife;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import ca.mahram.demo.butterknife.R;

public class GridDemo
  extends Activity {

    private LayoutInflater inflater;
    private Picasso        picasso;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_grid_demo);

        getActionBar ().setDisplayHomeAsUpEnabled (true);

        picasso = DemoConstants.picasso (this);
        inflater = LayoutInflater.from (this);

        final GridView grid = ButterKnife.findById (this, android.R.id.list);
        grid.setAdapter (new DemoGridAdapter ());
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        return false;
    }

    private class DemoGridAdapter
      extends AbstractDemoAdapter {

        private final int width;
        private final int height;

        private DemoGridAdapter () {
            final Resources resources = getResources ();
            width = resources.getDimensionPixelSize (R.dimen.grid_item_width);
            height = resources.getDimensionPixelSize (R.dimen.grid_item_height);
        }

        @Override protected void bindView (final View row, final Uri item) {
            final ListRowTag tag = (ListRowTag) row.getTag ();

            picasso.load (item)
                   .error (R.drawable.error)
                   .resize (width, height)
                   .centerCrop ()
                   .into (tag.image);
            tag.title.setText (item.getLastPathSegment ());
        }

        @Override protected View newView (final ViewGroup parent) {
            final View view = inflater.inflate (R.layout.item_grid, parent, false);
            view.setTag (new ListRowTag (view));
            return view;
        }
    }
}
