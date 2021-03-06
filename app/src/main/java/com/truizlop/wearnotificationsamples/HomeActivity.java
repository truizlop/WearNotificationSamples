package com.truizlop.wearnotificationsamples;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
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

    @OnClick(R.id.pages_notification_button)
    public void onSendNotificationWithPagesClicked(){
        sendNotificationWithPages();
    }

    @OnClick(R.id.stack_notification_button)
    public void onSendStackedNotificationsClicked(){
        sendStackedNotifications();
    }

    @OnClick(R.id.predefined_choices_notification_button)
    public void onSendNotificationWithPredefinedChoicesClicked(){
        sendNotificationWithPredefinedChoices();
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

    private void sendBigNotification(){
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

    private void sendInboxNotification(){
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

    private void sendNotificationWithWearableFeatures(){
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

    private void sendNotificationWithPages(){
        String sender = "Walter White";
        String message = "I am the danger";
        String longMessage = "I am not in danger, Skyler. I am the danger. A guy opens his door and gets shot and you think that of me? No. I am the one who knocks!";
        String text = message + "\n" + longMessage;
        @DrawableRes int image = R.drawable.white;

        Intent viewIntent = ViewMessageActivity.getLaunchIntent(this, sender, text, image);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 8, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), image))
                        .setContentTitle(sender)
                        .setContentText(message)
                        .setContentIntent(viewPendingIntent);

        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.bigText(longMessage);

        Notification secondPageNotification =
                new NotificationCompat.Builder(this)
                        .setStyle(secondPageStyle)
                        .build();

        Notification notification = notificationBuilder
                .extend(new NotificationCompat.WearableExtender()
                        .addPage(secondPageNotification))
                .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        int notificationId = 700;
        notificationManager.notify(notificationId, notification);
    }

    private static final String GROUP_KEY_MESSAGES = "group_key_messages";

    private void sendStackedNotifications(){

        Notification notif = new NotificationCompat.Builder(this)
                .setContentTitle("New message from Jesse Pinkman")
                .setContentText("Let's cook")
                .setSmallIcon(R.drawable.ic_sms)
                .setGroup(GROUP_KEY_MESSAGES)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(800, notif);

        Notification notif2 = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from Skyler White")
                .setContentText("Where are you?")
                .setSmallIcon(R.drawable.ic_sms)
                .setGroup(GROUP_KEY_MESSAGES)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(801, notif2);

        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.ic_sms)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Jesse Pinkmak   Let's cook")
                        .addLine("Skyler White   Where are you?")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("walterwhite@heisenberg.com"))
                .setGroup(GROUP_KEY_MESSAGES)
                .setGroupSummary(true)
                .build();

        notificationManager.notify(802, summaryNotification);
    }

    private void sendNotificationWithPredefinedChoices(){

        String replyLabel = getResources().getString(R.string.reply_label);
        String[] replyChoices = getResources().getStringArray(R.array.reply_choices);

        RemoteInput remoteInput = new RemoteInput.Builder(ReplyActivity.EXTRA_VOICE_REPLY)
                .setLabel(replyLabel)
                .setChoices(replyChoices)
                .build();

        Intent replyIntent = new Intent(this, ReplyActivity.class);
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                        getString(R.string.reply_label), replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pinkman))
                        .setContentTitle("Jesse Pinkman")
                        .setContentText("Would you like to cook?")
                        .setAutoCancel(true)
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(900, notification);
    }
}
