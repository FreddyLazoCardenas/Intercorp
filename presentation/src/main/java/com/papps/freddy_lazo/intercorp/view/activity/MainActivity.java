package com.papps.freddy_lazo.intercorp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.papps.freddy_lazo.intercorp.R;

public class MainActivity extends BaseActivity {

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView(this);
        initUI();
    }

    @Override
    public void initUI() {

    }
}
