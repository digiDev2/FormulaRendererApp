package com.theleafapps.pro.formularendererapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theleafapps.pro.formularendererapp.models.FormulaResponse
import com.theleafapps.pro.formularendererapp.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var myResponse: MutableLiveData<Response<FormulaResponse>> = MutableLiveData()
    var imageResponse:MutableLiveData<String> = MutableLiveData()

    fun getFormulaData(q: RequestBody) {
        viewModelScope.launch {
            val response = repository.getFormulaData(q)
            if(response.isSuccessful){
                val resourceLocationValue = response.headers().get("x-resource-location")
                val responseValue = repository.getFormulaImage(resourceLocationValue ?: "")
                val imageLocation = responseValue.headers().get("content-location")

                imageResponse.value = imageLocation
            }
            else{

            }






        }
    }
}