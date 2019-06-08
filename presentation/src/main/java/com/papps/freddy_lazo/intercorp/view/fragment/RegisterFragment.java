package com.papps.freddy_lazo.intercorp.view.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerWelcomeFragmentComponent;
import com.papps.freddy_lazo.intercorp.presenter.RegisterPresenter;
import com.papps.freddy_lazo.intercorp.presenter.WelcomePresenter;
import com.papps.freddy_lazo.intercorp.view.activity.WelcomeActivity;
import com.papps.freddy_lazo.intercorp.view.interfaces.RegisterPresenterView;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterPresenterView, DatePickerDialog.OnDateSetListener {

    @Inject
    RegisterPresenter presenter;


    @BindView(R.id.et_birthday)
    EditText birthday;

    private WelcomeActivity activity;
    private FirebaseAuth firebaseAuth;

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
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
        firebaseAuth = FirebaseAuth.getInstance();
        activity = (WelcomeActivity) getActivity();
        presenter.setView(this);
        initUI();
    }

    @Override
    public void initUI() {
    }

    @OnClick(R.id.et_birthday)
    public void petBirthday() {
        navigator.navigateToDatePicker(this);
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        birthday.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
    }

    @OnClick(R.id.btn_register)
    public void btnRegister(){
        presenter.registerUser();
    }

    @Override
    public String getUserId() {
        return firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getUid() : null;
    }

    @Override
    public void successRequest() {

    }
}
