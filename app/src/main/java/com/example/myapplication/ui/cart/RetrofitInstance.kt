package com.example.myapplication.ui.cart

//import com.example.myapplication.ui.dashboard.ApiInterface
import ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://test.nop-station.store/api/")  // Ensure it ends with "/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}
