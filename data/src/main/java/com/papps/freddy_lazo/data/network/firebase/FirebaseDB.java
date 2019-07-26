package com.papps.freddy_lazo.data.network.firebase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;

import io.reactivex.Observable;

public interface FirebaseDB {

    Observable<Void> observeSetValue(DatabaseReference reference, Object object);

    Observable<Void> observeFirestoreSetValue(DocumentReference reference, Object object);

    Observable<DataSnapshot> observeValueEvent(Query query);

    Observable<DocumentSnapshot> observeFirestoreValueEvent(DocumentReference query);

}
