package com.example.taipeitravelapi.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val ext: String?,
    val src: String?,
    val subject: String?
) : Parcelable