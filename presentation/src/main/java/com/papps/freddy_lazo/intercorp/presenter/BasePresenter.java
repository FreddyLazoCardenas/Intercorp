package com.papps.freddy_lazo.intercorp.presenter;


import com.papps.freddy_lazo.intercorp.view.interfaces.BaseView;

public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    protected T view;


    @Override
    public void destroy() {
        disposeUseCases();
        view = null;
    }

    @Override
    public void setView(T view) {
        this.view = view;
    }

    protected void disposeUseCases() {

    }
}
