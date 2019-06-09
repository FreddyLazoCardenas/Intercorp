package com.papps.freddy_lazo.intercorp.view.interfaces;

import com.facebook.AccessToken;

import org.json.JSONObject;

public interface RegisterPresenterView extends LoadingView {
    String getUserId();

    void successRequest();

    AccessToken getAccessToken();

    String getName();

    String getLastName();

    String getBirthday();

    String getAge();

    void fillUi(JSONObject jsonObject);
}
