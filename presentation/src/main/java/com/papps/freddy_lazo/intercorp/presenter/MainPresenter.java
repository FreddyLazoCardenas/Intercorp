package com.papps.freddy_lazo.intercorp.presenter;


import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.IsUserRegister;
import com.papps.freddy_lazo.domain.interactor.UserData;
import com.papps.freddy_lazo.domain.model.User;
import com.papps.freddy_lazo.intercorp.model.mapper.UserModelMapper;
import com.papps.freddy_lazo.intercorp.view.interfaces.MainPresenterView;
import com.papps.freddy_lazo.intercorp.view.interfaces.WelcomePresenterView;

import javax.inject.Inject;


public class MainPresenter extends BasePresenter<MainPresenterView> {

    private final UserData userData;

    @Inject
    MainPresenter(UserData userData) {
        this.userData = userData;
    }

    @Override
    protected void disposeUseCases() {
        userData.dispose();
    }

    public void getUserData() {
        if (view.getUserId() != null) {
            userData.bindParams(view.getUserId());
            userData.execute(new userDataObservable());
        }
    }

    private class userDataObservable extends DefaultObserver<User> {

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
        public void onNext(User model) {
            super.onNext(model);
            view.hideLoading();
            view.successRequest(UserModelMapper.transform(model));
        }
    }
}
