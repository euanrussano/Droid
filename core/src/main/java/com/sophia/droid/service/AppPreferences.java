package com.sophia.droid.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {

    private static final String PREF_CAMERA_ON_DROID = "camera.droid";
    private static final String PREFS_NAME = "droidPreferences";


    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean isCameraOnDroid() {
        return getPrefs().getBoolean(PREF_CAMERA_ON_DROID, true);
    }

    public void setCameraOnDroid(boolean cameraOnDroid) {
        getPrefs().putBoolean(PREF_CAMERA_ON_DROID, cameraOnDroid);
        getPrefs().flush();
    }
}
