package com.swatitiwari.tracktofit.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bmi_users")
public class bmi_pojo implements Parcelable{
    @ColumnInfo(name = "username")
    private final String username;
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "email")
    private final String email;
    @ColumnInfo(name = "password")
    private String password;

    public bmi_pojo(String username, @NonNull String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
