package com.papps.freddy_lazo.intercorp.view.interfaces;

import com.papps.freddy_lazo.intercorp.model.UserModel;

public interface MainPresenterView extends LoadingView {

    String getUserId();

    void successRequest(UserModel model);
}
