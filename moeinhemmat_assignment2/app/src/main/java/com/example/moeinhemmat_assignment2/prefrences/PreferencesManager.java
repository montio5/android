package com.example.moeinhemmat_assignment2.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.moeinhemmat_assignment2.entities.User;

public class PreferencesManager {

    private static final String PREF_FILE_NAME = "com.example.moeinhemmat_assignment2.prefrences.SHARED_PREFRENCES";
    public static final String PREF_KEY_FIRST_NAME = "first_name";
    public static final String PREF_KEY_LAST_NAME = "last_name";
    public static final String PREF_KEY_EMAIL = "email";
    public static final String PREF_KEY_PASSWORD = "password";
    public static final String PREF_KEY_CONFIRMATION = "confirmation";
    public static final String PREF_KEY_GENDER = "gender";
    public static final String PREF_KEY_IS_LOGIN = "is_login";

    private static PreferencesManager instance;
    private final SharedPreferences sharedPreferences;

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context.getApplicationContext());
        }
        return instance;
    }

    public <T> void put(String key, T value) {

        if (value instanceof String) {
            sharedPreferences.edit().putString(key, (String) value).apply();
            return;
        }

        if (value instanceof Integer) {
            sharedPreferences.edit().putInt(key, (Integer) value).apply();
            return;
        }

        if (value instanceof Boolean) {
            sharedPreferences.edit().putBoolean(key, (Boolean) value).apply();
            return;
        }
    }

    public <T> T get(String key, T defaultValue) {
        if (defaultValue instanceof String) {
            return (T) sharedPreferences.getString(key, (String) defaultValue);
        }

        if (defaultValue instanceof Integer) {
            Integer result = sharedPreferences.getInt(key, (Integer) defaultValue);
            return (T) result;
        }

        if (defaultValue instanceof Boolean) {
            Boolean result = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
            return (T) result;
        }
        return null;
    }

    public User getUserFromPreferences(){
        User user = new User();
        user.setFirstName(instance.get(PREF_KEY_FIRST_NAME, ""));
        user.setLastName(instance.get(PREF_KEY_LAST_NAME, ""));
        user.setEmail(instance.get(PREF_KEY_EMAIL, ""));
        user.setConfirmationType(instance.get(PREF_KEY_CONFIRMATION, ""));
        user.setGender(instance.get(PREF_KEY_GENDER, ""));
        return user;
    }
}

