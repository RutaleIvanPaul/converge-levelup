package com.example.ivanpaulrutale.convergelevelup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Intent intent = getIntent();
        String url = intent.getStringExtra("profile_url");
        TextView textView = findViewById(R.id.profile_url_link);
        textView.setText(url);
    }
}
