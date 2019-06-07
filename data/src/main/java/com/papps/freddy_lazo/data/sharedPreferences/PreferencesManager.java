package com.papps.freddy_lazo.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

@Singleton
public class PreferencesManager {

    private static final String KEY_DOCTOR_CURRENT_USER = "key_doctor_current_user";
    private static final String KEY_PET_LOVER_CURRENT_USER = "key_pet_lover_current_user";
    private static final String NAME_CURRENT_USER = "key_current_user";
    private final Context context;


    @Inject
    public PreferencesManager(Context context) {
        this.context = context;
    }

}