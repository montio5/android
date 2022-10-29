package com.example.moein_hemmat_assignment1.activities;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moein_hemmat_assignment1.R;

public class MainActivity extends AppCompatActivity {

    private final int URL_REQ_CODE = 1;

    private Button openUrlBtn;
    private TextView urlTv;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openUrlBtn = findViewById(R.id.open_url_button);
        urlTv = findViewById(R.id.url_edit_text);
    }

    public void onEnterUrlClicked(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, URL_REQ_CODE);
    }

    public void onOpenUrlClicked(View view) {
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == URL_REQ_CODE && data != null) {
            visibleUrlRelatedViews();
            url = data.getStringExtra(SecondActivity.URL);
            urlTv.setText(url);
        }
    }

    private void visibleUrlRelatedViews() {
        urlTv.setVisibility(View.VISIBLE);
        openUrlBtn.setVisibility(View.VISIBLE);
    }
}