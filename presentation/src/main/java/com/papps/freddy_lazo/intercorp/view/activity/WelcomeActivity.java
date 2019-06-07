package com.papps.freddy_lazo.intercorp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.intercorp.R;

public class WelcomeActivity extends BaseActivity {

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, WelcomeActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        injectView(this);
        initUI();
    }

    @Override
    public void initUI() {
        navigator.navigateToWelcomeFragment(this);
    }
}
