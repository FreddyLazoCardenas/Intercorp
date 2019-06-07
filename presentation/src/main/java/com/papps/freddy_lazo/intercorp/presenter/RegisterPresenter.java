package com.papps.freddy_lazo.intercorp.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.RegisterUser;
import com.papps.freddy_lazo.intercorp.view.interfaces.RegisterPresenterView;

import javax.inject.Inject;


public class RegisterPresenter extends BasePresenter<RegisterPresenterView> {

    private final RegisterUser registerUser;

    @Inject
    public RegisterPresenter(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @Override
    protected void disposeUseCases() {
        registerUser.dispose();
    }

    public void registerUser(){
        registerUser.bindParams(0L , null);
        registerUser.execute(new RegisterUserObservable());
    }

    private class RegisterUserObservable extends DefaultObserver<Void> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
