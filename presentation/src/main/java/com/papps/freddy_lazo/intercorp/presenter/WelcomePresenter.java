package com.papps.freddy_lazo.intercorp.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.papps.freddy_lazo.intercorp.R;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;

import org.json.JSONObject;

import javax.inject.Inject;


public class WelcomePresenter extends BasePresenter<WelcomePresenterView> {

    private final LoginManager facebookLoginManager;
    private final CallbackManager callbackManager;
    private final FirebaseAuth firebaseAuth;

    @Inject
    public WelcomePresenter() {
        this.facebookLoginManager = LoginManager.getInstance();
        this.callbackManager = CallbackManager.Factory.create();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void disposeUseCases() {
        facebookLoginManager.unregisterCallback(callbackManager);
    }

    public void connectFacebookManager() {
        facebookLoginManager.registerCallback(callbackManager, new FacebookCallbackComplete());
    }

    public void signOutWithFacebook() {
        facebookLoginManager.logOut();
    }

    public void handleSignInWithFacebook(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public final class FacebookCallbackComplete implements FacebookCallback<LoginResult> {

        @Override
        public void onSuccess(LoginResult loginResult) {
            handleFacebookAccessToken(loginResult.getAccessToken());
        }

        @Override
        public void onCancel() {
            view.showErrorMessage(view.context().getString(R.string.text_default_detail));
            //default implementation
        }

        @Override
        public void onError(FacebookException e) {
            view.showErrorMessage(view.context().getString(R.string.text_default_detail));
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                    } else {
                        // If sign in fails, display a message to the user.
                    }

                    // ...
                });
    }

    public final class FacebookGraphCallback implements GraphRequest.GraphJSONObjectCallback {

        @Override
        public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
            if (view == null) {
                return;
            }
            if (jsonObject != null) {
                view.facebookSignInSuccessful(jsonObject);
            } else {
                view.showErrorMessage(view.context().getString(R.string.text_default_detail));
            }
        }
    }


}
