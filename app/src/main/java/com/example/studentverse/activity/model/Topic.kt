package com.example.studentverse.activity.model

import android.os.Parcel
import android.os.Parcelable

data class Topic (
    val _id: String? =null,
    val name: String? = null,
    val description: String? = null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Topic> {
        override fun createFromParcel(parcel: Parcel): Topic {
            return Topic(parcel)
        }

        override fun newArray(size: Int): Array<Topic?> {
            return arrayOfNulls(size)
        }
    }
}