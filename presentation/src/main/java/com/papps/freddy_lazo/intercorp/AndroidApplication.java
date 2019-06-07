package com.papps.freddy_lazo.intercorp;

import android.app.Application;

import com.papps.freddy_lazo.intercorp.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerApplicationComponent;
import com.papps.freddy_lazo.intercorp.internal.dagger.module.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

}
