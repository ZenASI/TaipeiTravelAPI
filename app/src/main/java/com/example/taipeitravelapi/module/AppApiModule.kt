package com.example.taipeitravelapi.module

import com.example.taipeitravelapi.model.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface AppApiModule {


    @GET("{lang}/Attractions/All")
    @Headers("accept: application/json")
    suspend fun attractions(
        @Path(value = "lang", encoded = true) lang: String,
        @QueryMap(encoded = true) data: Map<String, String>
    ): Response<BaseResponse>
}