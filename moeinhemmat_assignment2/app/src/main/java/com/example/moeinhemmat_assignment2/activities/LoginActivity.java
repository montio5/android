package com.example.moeinhemmat_assignment2.activities;

import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_EMAIL;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_IS_LOGIN;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_PASSWORD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.ObjectsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moeinhemmat_assignment2.R;
import com.example.moeinhemmat_assignment2.prefrences.PreferencesManager;

public class LoginActivity extends AppCompatActivity {
    private TextView email_tv;
    private TextView password_tv;
    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_tv = findViewById(R.id.email_et);
        password_tv = findViewById(R.id.password_et);
        preferencesManager=PreferencesManager.getInstance(LoginActivity.this);
        autoLogin();
    }

    public void onSignUpBtn(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void onSignInBtn(View view) {
        String email = email_tv.getText().toString();
        String password = password_tv.getText().toString();
        login(email, password);

    }

    private void autoLogin() {
        boolean isLogin = preferencesManager.get(PREF_KEY_IS_LOGIN, false);
        if (isLogin) {
            String savedUsername = preferencesManager.get(PREF_KEY_EMAIL, "");
            String savedPassword = preferencesManager.get(PREF_KEY_PASSWORD, "");
            login(savedUsername, savedPassword);
        }
    }

    private void login(String username, String password) {
        String savedUsername = preferencesManager.get(PREF_KEY_EMAIL, "");
        String savedPassword = preferencesManager.get(PREF_KEY_PASSWORD, "");

        if (ObjectsCompat.equals(username, savedUsername)
                && ObjectsCompat.equals(password, savedPassword)) {
            preferencesManager.put(PREF_KEY_IS_LOGIN, true);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.error_invalid_login, Toast.LENGTH_SHORT).show();
        }
    }

}