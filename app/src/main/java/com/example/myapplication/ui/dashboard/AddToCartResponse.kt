package com.example.myapplication.ui.dashboard
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("Data") val data: CartData? = null,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("ErrorList") val errorList: List<String> = emptyList()
)

data class CartData(
    @SerializedName("TotalShoppingCartProducts") val totalShoppingCartProducts: Int = 0,
    @SerializedName("TotalWishListProducts") val totalWishListProducts: Int = 0,
    @SerializedName("RedirectToDetailsPage") val redirectToDetailsPage: Boolean = false,
    @SerializedName("RedirectToWishListPage") val redirectToWishListPage: Boolean = false,
    @SerializedName("RedirectToShoppingCartPage") val redirectToShoppingCartPage: Boolean = false
)