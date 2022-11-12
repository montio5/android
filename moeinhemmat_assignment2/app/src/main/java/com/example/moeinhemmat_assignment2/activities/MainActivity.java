package com.example.moeinhemmat_assignment2.activities;

import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_FIRST_NAME;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_IS_LOGIN;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_LAST_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moeinhemmat_assignment2.R;
import com.example.moeinhemmat_assignment2.entities.User;
import com.example.moeinhemmat_assignment2.prefrences.PreferencesManager;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private PreferencesManager preferences;
    private TextView userNameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferencesManager.getInstance(mContext);
        userNameEt = findViewById(R.id.user_name_tv);
        welcomeUser();
    }

    private void welcomeUser() {
        mContext = MainActivity.this;
        String firstName = preferences.get(PREF_KEY_FIRST_NAME, "");
        String lastName = preferences.get(PREF_KEY_LAST_NAME, "");
        String message = "Welcome " + firstName + " " + lastName;
        userNameEt.setText(message);
    }

    public void onAllStoriesBtn(View view) {
        Toast.makeText(this, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

    public void onProfileBtn(View view) {
        User user = preferences.getUserFromPreferences();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getString(R.string.profile));
        builder.setMessage(user.toString());
        builder.setPositiveButton(R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onFavoriteBtn(View view) {
        Toast.makeText(this, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

    public void onMyStoriesBtn(View view) {
        Toast.makeText(this, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

    public void onImportExportBtn(View view) {
        Toast.makeText(this, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();

    }

    public void onLogoutBtn(View view) {
           preferences.put(PREF_KEY_IS_LOGIN, false);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
    }
}