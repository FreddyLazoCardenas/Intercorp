package com.papps.freddy_lazo.intercorp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.intercorp.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        injectView(this);
        setUpHandler();
    }

    private void setUpHandler() {
        Handler ha = new Handler();
        Runnable ru = this::splashLogic;
        ha.postDelayed(ru, 1000);
    }

    private void splashLogic() {
        if (preferencesManager.getCurrentUser() == null)
            navigator.navigateToWelcomeActivity(this);
        else
            navigator.navigateToMainActivity(this);
    }
}
