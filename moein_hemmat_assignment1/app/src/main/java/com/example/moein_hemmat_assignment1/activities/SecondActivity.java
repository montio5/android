package com.example.moein_hemmat_assignment1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moein_hemmat_assignment1.R;
import com.example.moein_hemmat_assignment1.exceptions.EmptyUrlException;
import com.example.moein_hemmat_assignment1.exceptions.InvalidUrlException;

public class SecondActivity extends AppCompatActivity {
    private EditText urlEdt;
    private String url;
    public static final String URL = "com.example.moein_hemmat_assignment1.URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        urlEdt = findViewById(R.id.enter_url_edt);
    }

    public void onConfirmClicked(View view) {
        try {
            checkUrlValidation();

            returnUrlAndClose();
        } catch (EmptyUrlException e) {
            Toast.makeText(this, getString(R.string.error_required_url), Toast.LENGTH_SHORT).show();
        } catch (InvalidUrlException e) {
            Toast.makeText(this, getString(R.string.error_invalid_url), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkUrlValidation() throws EmptyUrlException, InvalidUrlException {
        url = urlEdt.getText().toString().trim();
        if (TextUtils.isEmpty(url)) {
            throw new EmptyUrlException();
        }
        if (!URLUtil.isValidUrl(url)) {
            throw new InvalidUrlException();
        }
    }

    private void returnUrlAndClose() {
        Intent intent = new Intent();
        intent.putExtra(this.URL, url);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}