package com.papps.freddy_lazo.intercorp.internal.dagger.module;

import android.content.Context;

import com.papps.freddy_lazo.data.network.firebase.References;
import com.papps.freddy_lazo.data.repository.UserDataRepository;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;
import com.papps.freddy_lazo.intercorp.AndroidApplication;
import com.papps.freddy_lazo.intercorp.UIThread;
import com.papps.freddy_lazo.data.executor.JobExecutor;
import com.papps.freddy_lazo.data.network.firebase.FirebaseDB;
import com.papps.freddy_lazo.data.network.firebase.FirebaseDBImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        this.mApplication = application;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }


    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    FirebaseDB provideFirebaseDB(FirebaseDBImpl firebaseDB) {
        return firebaseDB;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    References provideReferences() {
        return new References();
    }

    @Provides
    @Singleton
    PreferencesManager providePreferenceManager(Context context) {
        return new PreferencesManager(context);
    }

}

