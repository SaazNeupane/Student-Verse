package com.example.studentverse.activity.model

import android.os.Parcel
import android.os.Parcelable

data class Subject (
    val _id: String? =null,
    val name: String? = null,
    val description: String? = null,
    val pictureName: String? = null,
    val pictureId: String? = null,
    val topic:ArrayList<Topic>? =null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(pictureName)
        parcel.writeString(pictureId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Subject> {
        override fun createFromParcel(parcel: Parcel): Subject {
            return Subject(parcel)
        }

        override fun newArray(size: Int): Array<Subject?> {
            return arrayOfNulls(size)
        }
    }
}