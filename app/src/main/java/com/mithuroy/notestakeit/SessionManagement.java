package com.mithuroy.notestakeit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManagement {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String PREF_NAME = "com.mithuroy.notestakeit";
    private static final String IS_NOTE_TAKING_ACTIVATED = "IS_NOTE_TAKING_ACTIVATED";

    SessionManagement(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    void createNoteTakingSession() {
        editor.putBoolean(IS_NOTE_TAKING_ACTIVATED, true);
        editor.commit();
    }

    void checkNoteTakingSession() {
        if (!isNoteTakingSessionActivated()) {
            redirectToLoginActivity();
        }
    }

    void finishNoteTakingSession() {
        editor.clear();
        editor.commit();

        redirectToLoginActivity();
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private boolean isNoteTakingSessionActivated() {
        return sharedPreferences.getBoolean(IS_NOTE_TAKING_ACTIVATED, false);
    }


}
