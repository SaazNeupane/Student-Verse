package com.example.studentverse.activity.model

import android.os.Parcel
import android.os.Parcelable

data class Post(
    val _id: String? = null,
    val title: String? = null,
    val body: String? = null,
    val author: String? = null,
    val createdAt: String? = null,
    val tags: List<String>? = null,
    val answer: ArrayList<Answer>? =null,
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeString(author)
        parcel.writeString(createdAt)
        parcel.writeStringList(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}