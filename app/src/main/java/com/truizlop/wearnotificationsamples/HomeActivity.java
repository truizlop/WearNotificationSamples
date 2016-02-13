package com.truizlop.wearnotificationsamples;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.plain_notification_button)
    public void onSendPlainNotificationClicked(){
        sendPlainNotification();
    }

    @OnClick(R.id.action_notification_button)
    public void onSendActionNotificationClicked(){
        sendNotificationWithAction();
    }

    private void sendPlainNotification(){
        String sender = "Jesse Pinkman";
        String message = "Yo! Mr. White!";
        @DrawableRes int image = R.drawable.pinkman;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, message, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 100;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void sendNotificationWithAction(){
        String sender = "Jesse Pinkman";
        String message = "I am waiting for you!";
        @DrawableRes int image = R.drawable.pinkman;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, message, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 1, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        String albuquerque = "35.085334,-106.605553";
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(albuquerque));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 2, mapIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_map, getString(R.string.map), mapPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 200;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
