package com.papps.freddy_lazo.data.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class References {

    private DatabaseReference usersReference;

    public References() {
        this.usersReference = FirebaseDatabase.getInstance().getReference().child(Fields.USERS.getField());
        this.usersReference.keepSynced(true);
    }

    public DatabaseReference getUsersReference() {
        return usersReference;
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
