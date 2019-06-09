package com.papps.freddy_lazo.intercorp.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.RegisterUser;
import com.papps.freddy_lazo.intercorp.R;
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
        validateUserData();
    }

    private void validateUserData() {
        if (!isValidUserId())
            return;
        if(isValidField(view.getName()))
            return;
        if(isValidField(view.getLastName()))
            return;
        if(isValidField(view.getAge()))
            return;
        if(isValidField(view.getBirthday()))
            return;
        sendRegisterRequest();
    }

    private void sendRegisterRequest() {
        registerUser.bindParams(view.getUserId(), new UserModel(view.getName(), view.getLastName(), view.getAge(), view.getBirthday()));
        registerUser.execute(new RegisterUserObservable());
    }

    private boolean isValidField(String field) {
        if (TextUtils.isEmpty(field.trim())) {
            view.showErrorMessage(view.context().getString(R.string.fill_all_fields));
            return true;
        }
        return false;
    }

    private boolean isValidUserId() {
        return view.getUserId() != null;
    }

    private class RegisterUserObservable extends DefaultObserver<Void> {

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
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
            view.successRequest();
        }
    }
}
