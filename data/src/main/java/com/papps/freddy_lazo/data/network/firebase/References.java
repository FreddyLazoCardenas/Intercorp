package com.papps.freddy_lazo.data.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class References {

    private DatabaseReference usersReference;
    private CollectionReference collectionReference;

    public References() {
        this.collectionReference = FirebaseFirestore.getInstance().collection(Fields.USERS.getField());
        this.usersReference = FirebaseDatabase.getInstance().getReference().child(Fields.USERS.getField());
        this.usersReference.keepSynced(true);
    }

    public DatabaseReference getUsersReference(String key) {
        return usersReference.child(key);
    }

    public DocumentReference getUserReference(String userId) {
        return collectionReference.document(userId);
    }

    public enum Fields {
        USERS("users");

        private String field;

        Fields(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }

}
