package com.papps.freddy_lazo.data.network.firebase;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class FirebaseDBImpl implements FirebaseDB {

    @Inject
    FirebaseDBImpl() {
        //empty constructor
    }

    @Override
    public Observable<Void> observeSetValue(final DatabaseReference reference, final Object object) {
        return Observable.create(emitter -> reference.setValue(object, (databaseError, databaseReference) -> {
            if (isSuccess(emitter, databaseError)) {
                emitter.onComplete();
            }
        }));
    }

    private boolean isSuccess(ObservableEmitter emitter, DatabaseError error) {
        if (error == null) return true;
        if (!emitter.isDisposed()) emitter.onError(new Exception(error.getMessage()));
        return false;
    }
}
