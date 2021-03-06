package ca.mahram.demo.picasso;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ListDemo
  extends Activity {

    private LayoutInflater inflater;
    private Picasso        picasso;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_demo);

        getActionBar ().setDisplayHomeAsUpEnabled (true);

        picasso = DemoConstants.picasso (this);
        inflater = LayoutInflater.from (this);

        final ListView list = (ListView) findViewById (android.R.id.list);
        list.setAdapter (new DemoListAdapter ());
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        return false;
    }

    private class DemoListAdapter
      extends AbstractDemoAdapter {

        @Override protected void bindView (final View row, final Uri item) {
            final ListRowTag tag = (ListRowTag) row.getTag ();

            picasso.load (item)
                   .error (R.drawable.error)
                   .into (tag.image);
            tag.title.setText (item.toString ());
        }

        @Override protected View newView (final ViewGroup parent) {
            final View view = inflater.inflate (R.layout.item_list, parent, false);
            view.setTag (new ListRowTag (view));
            return view;
        }
    }
}
