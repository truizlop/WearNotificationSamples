package com.truizlop.wearnotificationsamples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewMessageActivity extends AppCompatActivity {

    private static final String EXTRA_SENDER = "ViewMessageActivity.EXTRA_SENDER";
    private static final String EXTRA_MESSAGE = "ViewMessageActivity.EXTRA_MESSAGE";
    private static final String EXTRA_IMAGE = "ViewMessageActivity.EXTRA_IMAGE";

    public static Intent getLaunchIntent(Context context, String sender, String message, @DrawableRes int image){
        Intent intent = new Intent(context, ViewMessageActivity.class);
        intent.putExtra(EXTRA_SENDER, sender);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_IMAGE, image);
        return intent;
    }

    @Bind(R.id.user_image) ImageView userImage;
    @Bind(R.id.name_text) TextView nameText;
    @Bind(R.id.message_text) TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userImage.setImageResource(intent.getIntExtra(EXTRA_IMAGE, 0));
        nameText.setText(intent.getStringExtra(EXTRA_SENDER));
        messageText.setText(intent.getStringExtra(EXTRA_MESSAGE));
    }
}
