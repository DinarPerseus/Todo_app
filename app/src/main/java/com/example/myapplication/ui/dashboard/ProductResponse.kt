package com.example.myapplication.ui.dashboard

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("Data") val data: List<Product>?,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("ErrorList") val errorList: List<String> = emptyList()
)

data class Product(
    @SerializedName("Name") val name: String = "",
    @SerializedName("ShortDescription") val shortDescription: String = "",
    @SerializedName("FullDescription") val fullDescription: String = "",
    @SerializedName("SeName") val seName: String = "",
    @SerializedName("Sku") val sku: String = "",
    @SerializedName("ProductType") val productType: Int = 0,
    @SerializedName("MarkAsNew") val markAsNew: Boolean = false,
    @SerializedName("ProductPrice") val productPrice: ProductPrice? = null,
    @SerializedName("PictureModels") val pictureModels: List<PictureModel> = emptyList(),
    @SerializedName("ProductSpecificationModel") val productSpecificationModel: SpecificationModel? = null,
    @SerializedName("ReviewOverviewModel") val reviewOverviewModel: ReviewOverviewModel? = null,
    @SerializedName("Id") val id: Int = 0
)

data class ProductPrice(
    @SerializedName("Price") val price: String = "",
    @SerializedName("PriceValue") val priceValue: Double = 0.0,
    @SerializedName("CurrencyCode") val currencyCode: String = "USD"
)

data class PictureModel(
    @SerializedName("ImageUrl") val imageUrl: String = "",
    @SerializedName("FullSizeImageUrl") val fullSizeImageUrl: String = ""
)

data class SpecificationModel(
    @SerializedName("Groups") val groups: List<Any> = emptyList()
)

data class ReviewOverviewModel(
    @SerializedName("ProductId") val productId: Int = 0,
    @SerializedName("RatingSum") val ratingSum: Int = 0,
    @SerializedName("TotalReviews") val totalReviews: Int = 0
)

