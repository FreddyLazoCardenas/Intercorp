package com.papps.freddy_lazo.intercorp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class UserModel implements Parcelable {

    public String name;
    public String lastName;
    public String age;
    public String birthDate;


    public UserModel() {
    }

    public UserModel(String name, String lastName, String age, String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }


    public String getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, UserModel.class);
    }

    public static UserModel toModel(String srtUser) {
        return srtUser != null && !srtUser.isEmpty() ? new Gson().fromJson(srtUser, UserModel.class) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.lastName);
        dest.writeString(this.age);
        dest.writeString(this.birthDate);
    }

    protected UserModel(Parcel in) {
        this.name = in.readString();
        this.lastName = in.readString();
        this.age = in.readString();
        this.birthDate = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
