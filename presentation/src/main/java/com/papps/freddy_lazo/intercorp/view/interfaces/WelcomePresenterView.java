package com.papps.freddy_lazo.intercorp.view.interfaces;

import com.facebook.AccessToken;

public interface WelcomePresenterView extends LoadingView {

    String getUserId();

    void successRequest(Boolean aBoolean, AccessToken token);
}
