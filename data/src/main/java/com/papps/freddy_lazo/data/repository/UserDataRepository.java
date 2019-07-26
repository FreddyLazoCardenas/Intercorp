package com.papps.freddy_lazo.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.papps.freddy_lazo.data.network.firebase.FirebaseDB;
import com.papps.freddy_lazo.data.network.firebase.References;
import com.papps.freddy_lazo.domain.model.User;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

@Singleton
public class UserDataRepository implements UserRepository {

    private FirebaseDB firebaseDB;
    private References references;

    @Inject
    UserDataRepository(FirebaseDB firebaseDB, References references) {
        this.firebaseDB = firebaseDB;
        this.references = references;
    }

    @Override
    public Observable<Void> saveUser(String userId, Object userData) {
        //return firebaseDB.observeSetValue(references.getUsersReference(userId), userData);
        return firebaseDB.observeFirestoreSetValue(references.getUserReference(userId), userData);
    }

    @Override
    public Observable<Boolean> isUserRegister(String userId) {
        //return firebaseDB.observeValueEvent(references.getUsersReference(userId)).map(DataSnapshot::exists);
        return firebaseDB.observeFirestoreValueEvent(references.getUserReference(userId)).map(DocumentSnapshot::exists);
    }

    @Override
    public Observable<User> getUserData(String userId) {
        return firebaseDB.observeFirestoreValueEvent(references.getUserReference(userId)).map(documentSnapshot -> documentSnapshot.toObject(User.class));
    }

}
