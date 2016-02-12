package com.truizlop.wearnotificationsamples;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
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

    private void sendPlainNotification(){
        String sender = "Jesse Pinkman";
        String message = "Yo! Mr. White!";
        @DrawableRes int image = R.drawable.pinkman;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, message, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

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
}
