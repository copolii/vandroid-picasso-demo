package ca.mahram.demo.butterknife;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.mahram.demo.butterknife.R;

public class DownloadService
  extends IntentService {

    private static final String EXTRA_WIDTH  = "ca.mahram.demo.picasso.DownloadService.width";
    private static final String EXTRA_HEIGHT = "ca.mahram.demo.picasso.DownloadService.height";

    private Bitmap largeIcon = null;

    public static void download (final Context context, final Uri uri, final int w, final int h) {
        final Intent intent = new Intent (context, DownloadService.class);
        intent.setData (uri);

        if (w > 0 && h > 0) {
            intent.putExtra (EXTRA_WIDTH, w);
            intent.putExtra (EXTRA_HEIGHT, h);
        }

        context.startService (intent);
    }

    public DownloadService () {
        super ("DownloadService");
    }

    @Override public void onDestroy () {
        if (null != largeIcon) {
            largeIcon.recycle ();
        }

        super.onDestroy ();
    }

    @Override
    protected void onHandleIntent (Intent intent) {
        if (intent == null) {
            return;
        }

        final Uri uri = intent.getData ();

        if (null == uri) {
            return;
        }

        final int w = intent.getIntExtra (EXTRA_WIDTH, 0);
        final int h = intent.getIntExtra (EXTRA_HEIGHT, 0);

        final File file = new File (Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_PICTURES),
                                    uri.getLastPathSegment ());
        notifyStart (file);

        try {
            final Bitmap bitmap = h > 0 && w > 0
                                  ? DemoConstants.picasso (this).load (uri).resize (w, h).get ()
                                  : DemoConstants.picasso (this).load (uri).get ();

            final OutputStream stream = new FileOutputStream (file);
            bitmap.compress (Bitmap.CompressFormat.JPEG, 80, stream);
            stream.close ();

            notifySuccess (file);
        } catch (IOException e) {
            notifyFailed (e);
            e.printStackTrace ();
        }
    }

    private NotificationManager getNotificationManager () {
        return (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
    }

    private NotificationCompat.Builder baseNotification () {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder (this);

        if (null == largeIcon) {
            try {
                largeIcon = DemoConstants.picasso (this).load (R.drawable.ic_launcher).get ();
            } catch (IOException e) {
                e.printStackTrace ();
                largeIcon = null;
            }
        }

        return builder.setLargeIcon (largeIcon)
                      .setSmallIcon (R.drawable.ic_stat_download)
                      .setWhen (System.currentTimeMillis ());
    }

    private void notifyStart (final File file) {
        final NotificationCompat.Builder notification = baseNotification ();
        final String title = getString (R.string.download_started);
        notification.setOngoing (true)
                    .setProgress (100, 0, true)
                    .setContentTitle (title)
                    .setTicker (title)
                    .setSubText (file.getAbsolutePath ());

        getNotificationManager ().notify (R.id.download_notification, notification.build ());
    }

    private void notifySuccess (final File file) {
        final NotificationCompat.Builder notification = baseNotification ();
        final String title = getString (R.string.download_finished);

        final Intent view = new Intent (Intent.ACTION_VIEW);
        view.setDataAndType (Uri.fromFile (file), "image/*");
        view.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            final DisplayMetrics dm = new DisplayMetrics ();
            ((WindowManager) getSystemService (WINDOW_SERVICE)).getDefaultDisplay ().getMetrics (dm);

            final Bitmap thumb = DemoConstants.picasso (this)
                                              .load (file)
                                              .resize (dm.widthPixels,
                                                       getResources ()
                                                         .getDimensionPixelSize (R.dimen
                                                                                   .notification_large_thumb_height)
                                                      )
                                              .centerCrop ().get ();
            notification.setStyle (new NotificationCompat.BigPictureStyle ().bigPicture (thumb));
        } catch (IOException e) {
            e.printStackTrace ();
        }

        notification.setOngoing (false)
                    .setContentTitle (title)
                    .setContentIntent (PendingIntent.getActivity (this, 0, view, PendingIntent.FLAG_UPDATE_CURRENT))
                    .setTicker (title)
                    .setAutoCancel (true)
                    .setSubText (file.getAbsolutePath ());

        getNotificationManager ().notify (R.id.download_notification, notification.build ());
    }

    private void notifyFailed (final Exception e) {
        final NotificationCompat.Builder notification = baseNotification ();
        final String title = getString (R.string.download_failed);
        notification.setOngoing (false)
                    .setAutoCancel (true)
                    .setContentTitle (title)
                    .setTicker (title)
                    .setSubText (e.getMessage ());

        getNotificationManager ().notify (R.id.download_notification, notification.build ());
    }
}
