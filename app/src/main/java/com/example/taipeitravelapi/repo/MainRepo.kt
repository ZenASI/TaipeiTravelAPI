package com.example.taipeitravelapi.repo

import com.example.taipeitravelapi.LangDef
import com.example.taipeitravelapi.model.BaseResponse
import com.example.taipeitravelapi.module.AppApiModule
import com.example.taipeitravelapi.utils.ApiResult
import javax.inject.Inject

class MainRepo @Inject constructor(private val appApiModule: AppApiModule) : BaseRepo() {

    suspend fun getAttractionsList(
        lang: String,
        map: Map<String, String>
    ): ApiResult<BaseResponse> =
        handleApiResponse { appApiModule.attractions(lang, map) }
}