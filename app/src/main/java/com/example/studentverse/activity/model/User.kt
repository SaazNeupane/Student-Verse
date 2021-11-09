package com.example.studentverse.activity.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val _id: String? = null,
    val fname: String? = null,
    val lname: String? = null,
    val email: String? = null,
    val username: String? = null,
    val mobile: String? = null,
    val password: String? = null,
    val address:String? =null,
    val profilename:String? =null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(email)
        parcel.writeString(username)
        parcel.writeString(mobile)
        parcel.writeString(password)
        parcel.writeString(address)
        parcel.writeString(profilename)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}