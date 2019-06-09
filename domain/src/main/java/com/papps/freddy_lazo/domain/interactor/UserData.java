package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserData extends UseCase {

    private final UserRepository repository;
    private String userId;

    @Inject
    UserData(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String userId) {
        this.userId = userId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getUserData(userId);
    }
}
