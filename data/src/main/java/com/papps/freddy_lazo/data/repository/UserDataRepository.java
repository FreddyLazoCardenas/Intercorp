package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.network.firebase.FirebaseDB;
import com.papps.freddy_lazo.data.network.firebase.References;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDataRepository implements UserRepository {

    private FirebaseDB firebaseDB;
    private References references;

    @Inject
    UserDataRepository(FirebaseDB firebaseDB, References references) {
        this.firebaseDB = firebaseDB;
        this.references = references;
    }

    @Override
    public Observable<Void> saveUser(long userId, Object userData) {
        return firebaseDB.observeSetValue(references.getUsersReference(), userData);
    }
}
