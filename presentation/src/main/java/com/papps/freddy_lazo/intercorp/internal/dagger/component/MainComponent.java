package com.papps.freddy_lazo.intercorp.internal.dagger.component;

import com.papps.freddy_lazo.intercorp.internal.dagger.PerActivity;
import com.papps.freddy_lazo.intercorp.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

}
