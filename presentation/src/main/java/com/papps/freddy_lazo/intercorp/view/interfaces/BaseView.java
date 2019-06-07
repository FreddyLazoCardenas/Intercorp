package com.papps.freddy_lazo.intercorp.view.interfaces;

import android.content.Context;

public interface BaseView {


    void initUI();

    Context context();

    void showErrorMessage(String message);

}
