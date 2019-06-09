package com.papps.freddy_lazo.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;


@Singleton
public class PreferencesManager {

    //no code need

    private final Context context;


    @Inject
    public PreferencesManager(Context context) {
        this.context = context;
    }

}