package com.example.ivanpaulrutale.convergelevelup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivanpaulrutale.convergelevelup.R;

public class ProfileDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        final Intent intent = getIntent();
        final String url = intent.getStringExtra("profile_url");
        String image_url = intent.getStringExtra("image_url");
        final String username = intent.getStringExtra("username");
        TextView textView = findViewById(R.id.profile_url_link);
        ImageView imageView = findViewById(R.id.profile_image);
        Button shareButton = findViewById(R.id.share_button);
        textView.setText(url);
        Glide.with(this).load(image_url).into(imageView);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer @"+username+", "+url+".");
                startActivity(Intent.createChooser(shareIntent, "Share Developer's Information using"));

            }
        });
    }
}
