package com.example.moeinhemmat_assignment2.activities;

import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_CONFIRMATION;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_EMAIL;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_FIRST_NAME;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_GENDER;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_LAST_NAME;
import static com.example.moeinhemmat_assignment2.prefrences.PreferencesManager.PREF_KEY_PASSWORD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moeinhemmat_assignment2.R;
import com.example.moeinhemmat_assignment2.entities.User;
import com.example.moeinhemmat_assignment2.exceptions.InvalidUserException;
import com.example.moeinhemmat_assignment2.prefrences.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Validator;


public class SignupActivity extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Button confirmBtn;
    private TextView firstNameTv;
    private TextView lastNameTv;
    private TextView emailTv;
    private TextView passwordTv;
    private TextView confPasswordTv;
    private RadioButton maleRad;
    private RadioButton femaleRad;
    private CheckBox emailCheck;
    private CheckBox smsCheck;


    private Context context;
    private PreferencesManager preferences;
    private final int SIGN_UP_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        confirmBtn = findViewById(R.id.confirm_btn);
        firstNameTv = findViewById(R.id.first_name_et);
        lastNameTv = findViewById(R.id.last_name_et);
        emailTv = findViewById(R.id.email_et);
        passwordTv = findViewById(R.id.password_et);
        confPasswordTv = findViewById(R.id.confirm_password_et);
        maleRad = findViewById(R.id.male_rad);
        femaleRad = findViewById(R.id.female_rad);
        emailCheck = findViewById(R.id.email_check);
        smsCheck = findViewById(R.id.sms_check);
        preferences=PreferencesManager.getInstance(getApplicationContext());

    }

    public void onConfirmationBt(View view) {
        signupUser();
    }

    private void signupUser(){
        try {
            User user = readUserInformation();
            save(user);
            Toast.makeText(getApplicationContext(), getString(R.string.message_sign_up_successful), Toast.LENGTH_SHORT).show();
            removeErrors();
            finish();
        } catch (InvalidUserException e) {
//            e.printStackTrace();
        }
    }
    private List<String> getConfirmationList() {
        List<String> confirmationType = new ArrayList<>();
        if (smsCheck.isChecked()) {
            confirmationType.add(getString(R.string.email));
        }
        if (emailCheck.isChecked()) {
            confirmationType.add(getString(R.string.sms));
        }
        return confirmationType;
    }

    private String getGender() {
        if (maleRad.isChecked()) {
            return maleRad.getText().toString();
        } else if (femaleRad.isChecked()) {
            return femaleRad.getText().toString();
        }
        return null;
    }

    private void save(User user) {
        preferences.put(PREF_KEY_FIRST_NAME, user.getFirstName());
        preferences.put(PREF_KEY_LAST_NAME, user.getLastName());
        preferences.put(PREF_KEY_EMAIL, user.getEmail());
        preferences.put(PREF_KEY_PASSWORD, user.getPassword());
        preferences.put(PREF_KEY_CONFIRMATION, user.getConfirmationType());
        preferences.put(PREF_KEY_GENDER, user.getGender());
    }
    private void removeErrors() {
        firstNameTv.setError(null);
        lastNameTv.setError(null);
        emailTv.setError(null);
        passwordTv.setError(null);
        confPasswordTv.setError(null);
    }
    private User readUserInformation() throws InvalidUserException {
        User user = new User();

        String firstName = firstNameTv.getText().toString();
        String lastName = lastNameTv.getText().toString();
        String email = emailTv.getText().toString();
        String password = passwordTv.getText().toString();
        String confirmPassword = confPasswordTv.getText().toString();
        String gender = getGender();
        List<String> confirmationType = getConfirmationList();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setConfirmationType(TextUtils.join(",", confirmationType));
        user.setEmail(email);
        user.setPassword(password);

        boolean isValidInformation = validateSignupInput(user, confirmPassword);
        if (isValidInformation) {
            return user;
        }
        throw new InvalidUserException();
    }
    private boolean validateSignupInput(User user, String confirmPassword) {
        boolean result = true;

        if (!user.getPassword().equals(confirmPassword)) {
            Toast.makeText(this, getString(R.string.passwords_not_sync), Toast.LENGTH_SHORT).show();
            result = false;
        }
        if (!user.getEmail().matches(emailPattern)) {
            Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }
}