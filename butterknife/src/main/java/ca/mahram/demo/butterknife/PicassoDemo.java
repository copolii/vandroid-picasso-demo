package ca.mahram.demo.butterknife;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class PicassoDemo
  extends Activity
  implements Callback {

    @InjectView (R.id.top_image)         ImageView topImage;
    @InjectView (android.R.id.summary)   TextView  summary;
    @InjectView (R.id.apply_bw)          CheckBox  applyBwTransformation;
    @InjectView (R.id.maybe_invalid)     CheckBox  invalidMaybe;
    @InjectView (R.id.chance_of_invalid) EditText  chanceOfInvalid;
    @InjectView (R.id.fit_mode_spinner)  Spinner   fitMode;
    @InjectView (R.id.resize)            CheckBox  resize;
    @InjectView (R.id.resize_row)        TableRow  resizeRow;
    @InjectView (R.id.width)             EditText  width;
    @InjectView (R.id.height)            EditText  height;

    Picasso picasso;

    private Uri currentImageUri;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_picasso_demo);
        ButterKnife.inject (this);

        picasso = DemoConstants.picasso (this);
        currentImageUri = DemoConstants.randomImageUri ();

        picasso.load (currentImageUri)
               .placeholder (R.drawable.downloading)
               .error (R.drawable.error)
               .transform (new GrayscaleTransformation ())
               .into (topImage);
    }

    @OnCheckedChanged (R.id.resize)
    public void resizeToggled (final CompoundButton chk, final boolean enabled) {
        resizeRow.setVisibility (enabled ? View.VISIBLE : View.GONE);
        width.setEnabled (enabled);
        height.setEnabled (enabled);

        if (!enabled) {
            return;
        }

        width.setText (String.valueOf (topImage.getMeasuredWidth ()));
        height.setText (String.valueOf (topImage.getMeasuredHeight ()));
    }

    @OnCheckedChanged (R.id.maybe_invalid)
    public void invalidMaybeToggled (final CompoundButton chk, final boolean enabled) {
        chanceOfInvalid.setEnabled (enabled);

        if (enabled) {
            chanceOfInvalid.setText ("0.0");
        } else {
            chanceOfInvalid.setText ("");
        }
    }

    @Override public boolean onCreateOptionsMenu (final Menu menu) {
        getMenuInflater ().inflate (R.menu.main_menu, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override public boolean onPrepareOptionsMenu (final Menu menu) {
        return super.onPrepareOptionsMenu (menu);
    }

    @Override public boolean onOptionsItemSelected (final MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.menu_grid_demo:
                startActivity (new Intent (this, GridDemo.class));
                break;
            case R.id.menu_list_demo:
                startActivity (new Intent (this, ListDemo.class));
                break;
            case R.id.menu_save:
                if (resize.isChecked ()) {
                    final int w = Integer.parseInt (width.getText ().toString ());
                    final int h = Integer.parseInt (height.getText ().toString ());
                    DownloadService.download (this, currentImageUri, w, h);
                } else {
                    DownloadService.download (this, currentImageUri, 0, 0);
                }
                break;
            default:
                return super.onOptionsItemSelected (item);
        }

        return true;
    }

    @Override public void onSuccess () {
        summary.setText (R.string.load_finished);
    }

    @Override public void onError () {
        summary.setText (R.string.load_failed);
    }

    @OnClick (R.id.load_next)
    public void loadNext (final View view) {
        summary.setText (R.string.load_started);

        final float threshold = invalidMaybe.isChecked ()
                                ? Float.parseFloat (chanceOfInvalid.getText ().toString ())
                                : 0f;

        currentImageUri = threshold > 0f
                          ? DemoConstants.randomValidOrInvalidImageUri (threshold)
                          : DemoConstants.randomImageUri ();

        final RequestCreator request = picasso.load (currentImageUri)
                                              .placeholder (R.drawable.downloading)
                                              .error (R.drawable.error);

        if (applyBwTransformation.isChecked ()) {
            request.transform (new GrayscaleTransformation ());
        }

        final boolean resizeChecked = resize.isChecked ();

        if (resizeChecked) {
            final int w = Integer.parseInt (width.getText ().toString ());
            final int h = Integer.parseInt (height.getText ().toString ());
            request.resize (w, h);
        }

        switch (fitMode.getSelectedItemPosition ()) {
            case 1: // center inside
                if (resizeChecked) {
                    request.centerInside ();
                }
                break;
            case 2: // center crop
                if (resizeChecked) {
                    request.centerCrop ();
                }
                break;
            case 3: // fit
                if (!resizeChecked) {
                    request.fit ();
                }
                break;
            case 0: // none
            default:
                // nada
        }

        request.into (topImage, this);
    }
}
