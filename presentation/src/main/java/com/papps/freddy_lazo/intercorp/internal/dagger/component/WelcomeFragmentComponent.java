package com.papps.freddy_lazo.intercorp.internal.dagger.component;

import com.papps.freddy_lazo.intercorp.internal.dagger.PerFragment;
import com.papps.freddy_lazo.intercorp.view.fragment.RegisterFragment;
import com.papps.freddy_lazo.intercorp.view.fragment.WelcomeFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface WelcomeFragmentComponent {
    void inject(WelcomeFragment fragment);
    void inject(RegisterFragment fragment);

}
