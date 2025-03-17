package com.example.myapplication.ui.dashboard
import com.google.gson.annotations.SerializedName

data class FormValue(
    @SerializedName("Key") val key: String,
    @SerializedName("Value") val value: String
)

data class AddToCartRequest(
    @SerializedName("FormValues") val formValues: List<FormValue>
)