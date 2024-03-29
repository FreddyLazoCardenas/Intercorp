package com.papps.freddy_lazo.intercorp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.intercorp.AndroidApplication;
import com.papps.freddy_lazo.intercorp.navigation.Navigator;
import com.papps.freddy_lazo.intercorp.view.activity.BaseActivity;
import com.papps.freddy_lazo.intercorp.view.interfaces.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Inject
    public Navigator navigator;
    @Inject
    public PreferencesManager preferencesManager;

    private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getActivity().getApplication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showMessage(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initUI() {

    }

}
