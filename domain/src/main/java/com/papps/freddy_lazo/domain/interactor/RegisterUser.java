package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RegisterUser extends UseCase {

    private final UserRepository repository;
    private long userId;
    private Object userData;

    @Inject
    public RegisterUser(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(long userId, Object userData) {
        this.userId = userId;
        this.userData = userData;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.saveUser(userId, userData);
    }
}
