package com.papps.freddy_lazo.intercorp.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.RegisterUser;
import com.papps.freddy_lazo.intercorp.model.UserModel;
import com.papps.freddy_lazo.intercorp.view.interfaces.RegisterPresenterView;

import javax.inject.Inject;


public class RegisterPresenter extends BasePresenter<RegisterPresenterView> {

    private final RegisterUser registerUser;

    @Inject
    RegisterPresenter(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @Override
    protected void disposeUseCases() {
        registerUser.dispose();
    }

    public void registerUser() {
        if (view.getUserId() != null) {
            registerUser.bindParams(view.getUserId(), new UserModel("Freddy","Lazo",3,"12-12-98"));
            registerUser.execute(new RegisterUserObservable());
        }
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
            view.successRequest();
        }
    }
}
