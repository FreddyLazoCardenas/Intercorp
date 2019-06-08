package com.papps.freddy_lazo.intercorp.presenter;


import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.IsUserRegister;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;

import javax.inject.Inject;



public class WelcomePresenter extends BasePresenter<WelcomePresenterView> {

   private final IsUserRegister isUserRegister;

    @Inject
    WelcomePresenter(IsUserRegister isUserRegister) {
        this.isUserRegister = isUserRegister;
    }

    @Override
    protected void disposeUseCases() {
       isUserRegister.dispose();
    }

    public void isUserRegister() {
        if (view.getUserId() != null) {
            isUserRegister.bindParams(view.getUserId());
            isUserRegister.execute(new IsUserRegisterObservable());
        }
    }

    private class IsUserRegisterObservable extends DefaultObserver<Boolean> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.hideLoading();
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onNext(Boolean aBoolean) {
            super.onNext(aBoolean);
            view.successRequest(aBoolean);
        }
    }
}
