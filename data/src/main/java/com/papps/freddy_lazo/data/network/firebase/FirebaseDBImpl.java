package com.papps.freddy_lazo.data.network.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposables;

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

    @Override
    public Observable<DataSnapshot> observeValueEvent(final Query query) {
        return Observable.create(emitter -> {
            ValueEventListener valueEventListener = query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (isSubscribed(emitter, query, this)) {
                        emitter.onNext(dataSnapshot);
                    }
                }

                @Override
                public void onCancelled(final DatabaseError error) {
                    if (isSubscribed(emitter, query, this)) {
                        emitter.onError(new Exception(error.getMessage()));
                    }
                }
            });
            emitter.setDisposable(Disposables.fromAction(() -> query.removeEventListener(valueEventListener)));
        });
    }

    private boolean isSubscribed(ObservableEmitter emitter, Query query, ValueEventListener listener) {
        if (emitter.isDisposed()) {
            query.removeEventListener(listener);
            return false;
        }
        return true;
    }

    private boolean isSuccess(ObservableEmitter emitter, DatabaseError error) {
        if (error == null) return true;
        if (!emitter.isDisposed()) emitter.onError(new Exception(error.getMessage()));
        return false;
    }
}
