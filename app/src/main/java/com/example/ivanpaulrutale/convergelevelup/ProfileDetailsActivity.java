package com.example.ivanpaulrutale.convergelevelup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Intent intent = getIntent();
        String url = intent.getStringExtra("profile_url");
        String image_url = intent.getStringExtra("image_url");
        TextView textView = findViewById(R.id.profile_url_link);
        ImageView imageView = findViewById(R.id.profile_image);
        textView.setText(url);
        Glide.with(this).load(image_url).into(imageView);
    }
}
