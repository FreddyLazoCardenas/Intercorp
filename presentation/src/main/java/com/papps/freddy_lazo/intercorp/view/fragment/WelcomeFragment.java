package com.papps.freddy_lazo.intercorp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerWelcomeFragmentComponent;
import com.papps.freddy_lazo.intercorp.presenter.WelcomePresenter;
import com.papps.freddy_lazo.intercorp.view.activity.WelcomeActivity;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;

import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.OnClick;

public class WelcomeFragment extends BaseFragment implements WelcomePresenterView {


    @Inject
    WelcomePresenter presenter;

    private WelcomeActivity activity;

    public static Fragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerWelcomeFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (WelcomeActivity) getActivity();
        presenter.setView(this);
        initUI();
    }

    @Override
    public void initUI() {
        presenter.connectFacebookManager();
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @OnClick(R.id.login_button)
    public void loginButton() {
        navigator.navigateToFacebookSignInPin(activity);
    }

    @Override
    public void facebookSignInSuccessful(JSONObject jsonObject) {
        presenter.signOutWithFacebook();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleSignInWithFacebook(requestCode, resultCode, data);
    }
}
