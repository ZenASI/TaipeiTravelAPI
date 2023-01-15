package com.example.taipeitravelapi.model

import android.os.Parcel
import android.os.Parcelable

data class Target(
    val id: Int,
    val name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Target> {
        override fun createFromParcel(parcel: Parcel): Target {
            return Target(parcel)
        }

        override fun newArray(size: Int): Array<Target?> {
            return arrayOfNulls(size)
        }
    }
}
