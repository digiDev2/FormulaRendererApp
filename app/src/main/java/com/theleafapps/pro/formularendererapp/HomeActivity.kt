package com.theleafapps.pro.formularendererapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.theleafapps.pro.formularendererapp.repository.Repository
import com.theleafapps.pro.formularendererapp.viewmodel.MainViewModel
import com.theleafapps.pro.formularendererapp.viewmodel.MainViewModelFactory
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    private lateinit var renderHistoryBtn: ImageButton
    private lateinit var inputBox:EditText
    private lateinit var viewModel: MainViewModel
    private lateinit var result_image_iv:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBox = findViewById(R.id.formula_input_et)
        result_image_iv = findViewById(R.id.result_image_iv)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //val body = "{\"q\":\"a^2 + b^2 = c^2\"}"
        //val crud = "\lim_{h \to 0} \frac{f(a+h)-f(a)}{h}"

        //val body = "{\"q\":\"\\\\lim_{h \\\\to 0} \\\\frac{f(a+h)-f(a)}{h}}"
        //val crude = "\int_{a}^{b} x^2 dx"
        val inputText = inputBox.text
        val jsonObject = JSONObject()
        jsonObject.put("q",inputText)

        val input = jsonObject.toString()
        //input.length
        //val body  = "{\"q\":\"\\\\int_{a}^{b} x^2 dx\"}"
        //jsonObject.put("q","\lim_{h \to 0} \frac{f(a+h)-f(a)}{h}")

        val requestBody: RequestBody = RequestBody.create(
                MediaType.parse("text/plain"),input)

        viewModel.getFormulaData(requestBody)
        viewModel.imageResponse.observe(this, Observer { response ->
            if (response.isNotEmpty()) {
                Glide
                    .with(this)
                    .load(response)
                    .placeholder(R.drawable.math_default_image)
                    .into(result_image_iv);
            } else {
                Toast.makeText(this, "Error has occured", Toast.LENGTH_SHORT).show()
            }
        })

    }
}