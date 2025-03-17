//package com.example.myapplication.ui.dashboard
//
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import retrofit.GsonConverterFactory
//import retrofit.Retrofit
//
//
////object RetrofitInstance {
////    val api:ApiInterface by lazy{
////         Retrofit.Builder()
////            .baseUrl("http://test.nop-station.store/api")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////            .create(ApiInterface::class.java)
////    }
////}
//
////
//object RetrofitInstance {
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl("http://test.nop-station.store/api")
//            .addConverterFactory(GsonConverterFactory.create())
//            // Add this to handle suspend functions
//            .build()
//    }
//
//    val api: ApiInterface = retrofit.create(ApiInterface::class.java)
//}
//
//
////object RetrofitInstance {
////    private val retrofit by lazy {
////        Retrofit.Builder()
////            .baseUrl("http://test.nop-station.store/api") // Use your actual base URL
////            .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON serialization
////            .build()
////    }
////
////    val api: ApiInterface = retrofit.create(ApiInterface::class.java)
////}
//import com.example.myapplication.ui.dashboard.ApiInterface
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
