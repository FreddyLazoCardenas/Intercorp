package com.papps.freddy_lazo.intercorp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.internal.dagger.component.DaggerMainComponent;
import com.papps.freddy_lazo.intercorp.model.UserModel;
import com.papps.freddy_lazo.intercorp.presenter.MainPresenter;
import com.papps.freddy_lazo.intercorp.view.interfaces.MainPresenterView;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainPresenterView {

    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_last_name)
    TextView lastName;
    @BindView(R.id.tv_age)
    TextView age;
    @BindView(R.id.tv_birthday)
    TextView birthday;

    @Inject
    MainPresenter presenter;

    private FirebaseAuth firebaseAuth;


    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        firebaseAuth = FirebaseAuth.getInstance();
        presenter.setView(this);
        presenter.getUserData();
    }

    private void fillUi(UserModel model) {
        name.setText(Html.fromHtml(getString(R.string.name, model.getName())));
        lastName.setText(Html.fromHtml(getString(R.string.lastName, model.getLastName())));
        age.setText(Html.fromHtml(getString(R.string.age, model.getAge())));
        birthday.setText(Html.fromHtml(getString(R.string.birthday, model.getBirthDate())));
    }


    @OnClick(R.id.btn_log_out)
    public void logOut() {
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        navigator.navigateToWelcomeActivity(this);
    }

    @Override
    public String getUserId() {
        return firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getUid() : null;
    }

    @Override
    public void successRequest(UserModel model) {
        fillUi(model);
    }
}
