package com.papps.freddy_lazo.intercorp.view.interfaces;

public interface RegisterPresenterView extends LoadingView {
    String getUserId();

    void successRequest();

    String getName();

    String getLastName();

    String getBirthday();

    String getAge();
}
