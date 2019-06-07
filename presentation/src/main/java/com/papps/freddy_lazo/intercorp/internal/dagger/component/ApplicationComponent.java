package com.papps.freddy_lazo.intercorp.internal.dagger.component;


import android.content.Context;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;
import com.papps.freddy_lazo.intercorp.internal.dagger.module.ApplicationModule;
import com.papps.freddy_lazo.intercorp.navigation.Navigator;
import com.papps.freddy_lazo.intercorp.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;


/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    Navigator navigator();

    PreferencesManager preferenceManager();

    UserRepository userRepository();

}
