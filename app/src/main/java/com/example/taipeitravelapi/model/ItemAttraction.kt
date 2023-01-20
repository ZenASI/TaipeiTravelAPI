package com.example.taipeitravelapi.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable