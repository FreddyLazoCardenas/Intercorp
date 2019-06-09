package com.papps.freddy_lazo.intercorp.view.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerWelcomeFragmentComponent;
import com.papps.freddy_lazo.intercorp.presenter.RegisterPresenter;
import com.papps.freddy_lazo.intercorp.view.activity.WelcomeActivity;
import com.papps.freddy_lazo.intercorp.view.interfaces.RegisterPresenterView;

import org.json.JSONObject;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterPresenterView, DatePickerDialog.OnDateSetListener {

    @Inject
    RegisterPresenter presenter;


    @BindView(R.id.et_birthday)
    EditText birthday;
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.et_last_name)
    EditText lastName;
    @BindView(R.id.et_age)
    EditText age;

    private WelcomeActivity activity;
    private FirebaseAuth firebaseAuth;
    private static final String ARG_ACCESS_TOKEN = "user";


    public static RegisterFragment newInstance(AccessToken token) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ACCESS_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
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
        if (getArguments() != null) {
            firebaseAuth = FirebaseAuth.getInstance();
            activity = (WelcomeActivity) getActivity();
            presenter.setView(this);
            presenter.getFacebookData();
            initUI();
        }
    }

    @Override
    public void initUI() {
        // no code need
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
        activity.showLoading();
    }

    @Override
    public void hideLoading() {
        activity.hideLoading();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        birthday.setText(String.format("%d-%d-%d", dayOfMonth, month + 1, year));
    }

    @OnClick(R.id.btn_register)
    public void btnRegister() {
        presenter.registerUser();
    }

    @Override
    public String getUserId() {
        return firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getUid() : null;
    }

    @Override
    public void successRequest() {
        navigator.navigateToMainActivity(activity);
    }

    @Override
    public AccessToken getAccessToken() {
        return Objects.requireNonNull(getArguments()).getParcelable(ARG_ACCESS_TOKEN);
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @Override
    public String getBirthday() {
        return birthday.getText().toString();
    }

    @Override
    public String getAge() {
        return age.getText().toString();
    }

    @Override
    public void fillUi(JSONObject jsonObject) {
        if (jsonObject != null) {
            name.setText(jsonObject.optString("first_name"));
            lastName.setText(jsonObject.optString("last_name"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
