package com.papps.freddy_lazo.intercorp.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.facebook.login.LoginManager;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.view.activity.BaseActivity;
import com.papps.freddy_lazo.intercorp.view.activity.MainActivity;
import com.papps.freddy_lazo.intercorp.view.activity.WelcomeActivity;
import com.papps.freddy_lazo.intercorp.view.fragment.WelcomeFragment;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    //activities

    public void navigateToWelcomeActivity(BaseActivity activity) {
        activity.startActivity(WelcomeActivity.getCallingIntent(activity));
    }

    public void navigateToMainActivity(BaseActivity activity) {
        activity.startActivity(MainActivity.getCallingIntent(activity));
    }

    //fragments

    public void navigateToWelcomeFragment(BaseActivity activity) {
        fragmentTransaction(activity, WelcomeFragment.newInstance(), false);
    }

    //others

    public void navigateToFacebookSignInPin(BaseActivity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
    }

    void fragmentTransaction(BaseActivity activity, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) transaction.addToBackStack(fragment.getClass().getSimpleName());
        if (activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

}