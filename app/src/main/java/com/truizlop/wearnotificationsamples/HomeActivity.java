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

    @OnClick(R.id.wearable_action_notification_button)
    public void onSendWearableActionNotificationClicked(){
        sendNotificationWithActionInWearOnly();
    }

    @OnClick(R.id.big_notification_button)
    public void onSendBigNotificationClicked(){
        sendBigNotification();
    }

    @OnClick(R.id.inbox_notification_button)
    public void onSendInboxNotificationClicked(){
        sendInboxNotification();
    }

    @OnClick(R.id.wearable_extender_notification_button)
    public void onSendWearableExtenderNotificationClicked(){
        sendNotificationWithWearableFeatures();
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

    private void sendNotificationWithActionInWearOnly(){
        String sender = "Jesse Pinkman";
        String message = "I am waiting for you!";
        @DrawableRes int image = R.drawable.pinkman;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, message, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 3, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        String albuquerque = "35.085334,-106.605553";
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(albuquerque));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 4, mapIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_map,
                        getString(R.string.map), mapPendingIntent)
                        .build();

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent)
                        .extend(new NotificationCompat.WearableExtender().addAction(action));


        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 300;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void sendBigNotification(){
        String sender = "Walter White";
        String message = "I am the danger";
        String longMessage = "I am not in danger, Skyler. I am the danger. A guy opens his door and gets shot and you think that of me? No. I am the one who knocks!";
        String text = message + "\n" + longMessage;
        @DrawableRes int image = R.drawable.white;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, text, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 5, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(longMessage);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent)
                        .setStyle(bigStyle);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 400;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void sendInboxNotification(){
        String sender = "Walter White";
        String message = "I am the danger";
        String longMessage = "I am not in danger, Skyler. I am the danger. A guy opens his door and gets shot and you think that of me? No. I am the one who knocks!";
        String text = message + "\n" + longMessage;
        @DrawableRes int image = R.drawable.white;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, text, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 6, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle()
                    .addLine(message)
                    .addLine(longMessage)
                    .setSummaryText("+10 more");

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent)
                        .setStyle(inboxStyle);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 500;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void sendNotificationWithWearableFeatures(){
        String sender = "Jesse Pinkman";
        String message = "Yo! Mr. White!";
        @DrawableRes int image = R.drawable.pinkman;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, message, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 7, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), image));

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(viewPendingIntent)
                        .extend(wearableExtender);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 600;
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
