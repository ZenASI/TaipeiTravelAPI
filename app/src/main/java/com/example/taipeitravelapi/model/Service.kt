package com.example.taipeitravelapi.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Service(
    val id: Int,
    val name: String?
) : Parcelable