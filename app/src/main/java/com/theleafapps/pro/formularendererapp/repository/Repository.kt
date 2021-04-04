package com.theleafapps.pro.formularendererapp.repository

import com.theleafapps.pro.formularendererapp.models.FormulaResponse
import com.theleafapps.pro.formularendererapp.network.RetrofitInstance
import okhttp3.RequestBody
import retrofit2.Response

class Repository {
    suspend fun getFormulaData(q: RequestBody): Response<FormulaResponse> {
        return RetrofitInstance.api.getFormulaData(q)
    }

    suspend fun getFormulaImage(hash:String): Response<Void> {
        return RetrofitInstance.api.getFormulaImagePng(hash)
    }
}