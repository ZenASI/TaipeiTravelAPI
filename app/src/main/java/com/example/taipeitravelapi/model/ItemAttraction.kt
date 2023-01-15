package com.example.taipeitravelapi.model

import android.os.Parcel
import android.os.Parcelable

data class ItemAttraction(
    val address: String?,
    val category: List<Category>?,
    val distric: String?,
    val elong: Double,
    val email: String?,
    val facebook: String?,
    val fax: String?,
//    val files: List<Any>,
//    val friendly: List<Any>,
    val id: Int,
    val images: List<Image>?,
    val introduction: String?,
//    val links: List<Any>,
    val modified: String?,
    val months: String?,
    val name: String?,
//    val name_zh: Any,
    val nlat: Double,
    val official_site: String?,
    val open_status: Int,
    val open_time: String?,
    val remind: String?,
    val service: List<Service>?,
    val staytime: String?,
    val target: List<com.example.taipeitravelapi.model.Target>?,
    val tel: String?,
    val ticket: String?,
    val url: String?,
    val zipcode: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(Category),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createTypedArrayList(Image),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Service),
        parcel.readString(),
        parcel.createTypedArrayList(Target),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeTypedList(category)
        parcel.writeString(distric)
        parcel.writeDouble(elong)
        parcel.writeString(email)
        parcel.writeString(facebook)
        parcel.writeString(fax)
        parcel.writeInt(id)
        parcel.writeTypedList(images)
        parcel.writeString(introduction)
        parcel.writeString(modified)
        parcel.writeString(months)
        parcel.writeString(name)
        parcel.writeDouble(nlat)
        parcel.writeString(official_site)
        parcel.writeInt(open_status)
        parcel.writeString(open_time)
        parcel.writeString(remind)
        parcel.writeTypedList(service)
        parcel.writeString(staytime)
        parcel.writeTypedList(target)
        parcel.writeString(tel)
        parcel.writeString(ticket)
        parcel.writeString(url)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemAttraction> {
        override fun createFromParcel(parcel: Parcel): ItemAttraction {
            return ItemAttraction(parcel)
        }

        override fun newArray(size: Int): Array<ItemAttraction?> {
            return arrayOfNulls(size)
        }
    }
}