package com.papps.freddy_lazo.data.network.firebase;


import com.google.firebase.database.DatabaseReference;

import io.reactivex.Observable;

public interface FirebaseDB {

    Observable<Void> observeSetValue(DatabaseReference reference, Object object);

}
