package com.papps.freddy_lazo.intercorp.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.intercorp.AndroidApplication;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.intercorp.navigation.Navigator;
import com.papps.freddy_lazo.intercorp.view.interfaces.BaseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements BaseView {


    @Inject
    Navigator navigator;

    @Inject
    PreferencesManager preferencesManager;

    @Nullable
    @BindView(R.id.v_progress)
    View vProgress;

    protected boolean isStopped;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected void injectView(Activity activity) {
        ButterKnife.bind(activity);
    }


    @Override
    protected void onResume() {
        super.onResume();
        isStopped = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStopped = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStopped = true;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void showLoading() {
        runOnUiThread(() -> {
            if (vProgress != null) vProgress.setVisibility(View.VISIBLE);
        });
    }

    public void hideLoading() {
        if (vProgress != null) vProgress.setVisibility(View.GONE);
    }


    @Override
    public void initUI() {
        //no code need
    }

    @Override
    public Context context() {
        return this;
    }

    protected void showMessage(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(this, message);
    }
}
