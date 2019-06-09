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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerWelcomeFragmentComponent;
import com.papps.freddy_lazo.intercorp.presenter.WelcomePresenter;
import com.papps.freddy_lazo.intercorp.view.activity.WelcomeActivity;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;


import javax.inject.Inject;

import butterknife.BindView;

public class WelcomeFragment extends BaseFragment implements WelcomePresenterView, FacebookCallback<LoginResult> {

    @Inject
    WelcomePresenter presenter;

    @BindView(R.id.login_button)
    LoginButton loginButton;

    private WelcomeActivity activity;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;


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
        initFacebookSignIn();
    }

    private void initFacebookSignIn() {
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, this);
    }


    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //success
                        presenter.isUserRegister();
                    } else {
                        showErrorMessage(getString(R.string.text_default_detail));
                        // If sign in fails, display a message to the user.
                    }
                    // ...
                });
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        handleFacebookAccessToken(loginResult.getAccessToken());
    }

    @Override
    public void onCancel() {
        showErrorMessage(getString(R.string.text_default_detail));
    }

    @Override
    public void onError(FacebookException error) {
        showErrorMessage(error.getMessage());
    }

    @Override
    public String getUserId() {
        return firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getUid() : null;
    }

    @Override
    public void successRequest(Boolean isRegister) {
        if (!isRegister) {
            navigator.navigateToRegisterFragment(activity);
        }else{
            navigator.navigateToMainActivity(activity);
        }
    }

    @Override
    public void showLoading() {
        activity.showLoading();
    }

    @Override
    public void hideLoading() {
        activity.hideLoading();
    }
}
