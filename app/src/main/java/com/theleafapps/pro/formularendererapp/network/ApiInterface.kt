package com.theleafapps.pro.formularendererapp.network

import com.theleafapps.pro.formularendererapp.models.FormulaResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
    )
    @POST("media/math/check/tex")
    suspend fun getFormulaData(
        @Body q: RequestBody
    ):Response<FormulaResponse>

    // /media/math/render/svg/{hash}
    @GET("media/math/render/svg/{hash}")
    suspend fun getFormulaImageSvg(@Path("hash") hash:String):Response<String>

    // /media/math/render/png/{hash}
    @GET("media/math/render/png/{hash}")
    suspend fun getFormulaImagePng(@Path("hash") hash:String):Response<Void>
}