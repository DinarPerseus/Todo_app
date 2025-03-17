package com.example.myapplication.ui.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("Data") val data: CartData?,
    @SerializedName("Message") val message: String?,
    @SerializedName("ErrorList") val errorList: List<Any>?
)

data class CartData(
    @SerializedName("Cart") val cart: Cart?
)

data class Cart(
    @SerializedName("OnePageCheckoutEnabled") val onePageCheckoutEnabled: Boolean?,
    @SerializedName("ShowSku") val showSku: Boolean?,
    @SerializedName("ShowProductImages") val showProductImages: Boolean?,
    @SerializedName("IsEditable") val isEditable: Boolean?,
    @SerializedName("Items") val items: List<Item>?,
    @SerializedName("CheckoutAttributes") val checkoutAttributes: List<CheckoutAttribute>?,
    @SerializedName("Warnings") val warnings: List<Any>?,
    @SerializedName("MinOrderSubtotalWarning") val minOrderSubtotalWarning: String?,
    @SerializedName("DisplayTaxShippingInfo") val displayTaxShippingInfo: Boolean?,
    @SerializedName("TermsOfServiceOnShoppingCartPage") val termsOfServiceOnShoppingCartPage: Boolean?,
    @SerializedName("TermsOfServiceOnOrderConfirmPage") val termsOfServiceOnOrderConfirmPage: Boolean?,
    @SerializedName("TermsOfServicePopup") val termsOfServicePopup: Boolean?,
    @SerializedName("DiscountBox") val discountBox: DiscountBox?,
    @SerializedName("GiftCardBox") val giftCardBox: GiftCardBox?,
    @SerializedName("OrderReviewData") val orderReviewData: OrderReviewData?,
    @SerializedName("ButtonPaymentMethodViewComponents") val buttonPaymentMethodViewComponents: List<Any>?,
    @SerializedName("HideCheckoutButton") val hideCheckoutButton: Boolean?,
    @SerializedName("ShowVendorName") val showVendorName: Boolean?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class Item(
    @SerializedName("Sku") val sku: String?,
    @SerializedName("VendorName") val vendorName: String?,
    @SerializedName("Picture") val picture: Picture?,
    @SerializedName("ProductId") val productId: Int?,
    @SerializedName("ProductName") val productName: String?,
    @SerializedName("ProductSeName") val productSeName: String?,
    @SerializedName("UnitPrice") val unitPrice: String?,
    @SerializedName("UnitPriceValue") val unitPriceValue: Double?,
    @SerializedName("SubTotal") val subTotal: String?,
    @SerializedName("SubTotalValue") val subTotalValue: Double?,
    @SerializedName("Discount") val discount: Any?,
    @SerializedName("DiscountValue") val discountValue: Double?,
    @SerializedName("MaximumDiscountedQty") val maximumDiscountedQty: Any?,
    @SerializedName("Quantity") val quantity: Int?,
    @SerializedName("AllowedQuantities") val allowedQuantities: List<Any>?,
    @SerializedName("AttributeInfo") val attributeInfo: String?,
    @SerializedName("RecurringInfo") val recurringInfo: Any?,
    @SerializedName("RentalInfo") val rentalInfo: Any?,
    @SerializedName("AllowItemEditing") val allowItemEditing: Boolean?,
    @SerializedName("DisableRemoval") val disableRemoval: Boolean?,
    @SerializedName("Warnings") val warnings: List<Any>?,
    @SerializedName("Id") val id: Int?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class Picture(
    @SerializedName("ImageUrl") val imageUrl: String?,
    @SerializedName("ThumbImageUrl") val thumbImageUrl: String?,
    @SerializedName("FullSizeImageUrl") val fullSizeImageUrl: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("AlternateText") val alternateText: String?,
    @SerializedName("Id") val id: Int?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class CheckoutAttribute(
    @SerializedName("Name") val name: String?,
    @SerializedName("DefaultValue") val defaultValue: Any?,
    @SerializedName("TextPrompt") val textPrompt: Any?,
    @SerializedName("IsRequired") val isRequired: Boolean?,
    @SerializedName("SelectedDay") val selectedDay: Any?,
    @SerializedName("SelectedMonth") val selectedMonth: Any?,
    @SerializedName("SelectedYear") val selectedYear: Any?,
    @SerializedName("AllowedFileExtensions") val allowedFileExtensions: List<Any>?,
    @SerializedName("AttributeControlType") val attributeControlType: Int?,
    @SerializedName("Values") val values: List<AttributeValue>?,
    @SerializedName("Id") val id: Int?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class AttributeValue(
    @SerializedName("Name") val name: String?,
    @SerializedName("ColorSquaresRgb") val colorSquaresRgb: Any?,
    @SerializedName("PriceAdjustment") val priceAdjustment: String?,
    @SerializedName("IsPreSelected") val isPreSelected: Boolean?,
    @SerializedName("Id") val id: Int?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class DiscountBox(
    @SerializedName("AppliedDiscountsWithCodes") val appliedDiscountsWithCodes: List<Any>?,
    @SerializedName("Display") val display: Boolean?,
    @SerializedName("Messages") val messages: List<Any>?,
    @SerializedName("IsApplied") val isApplied: Boolean?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class GiftCardBox(
    @SerializedName("Display") val display: Boolean?,
    @SerializedName("Message") val message: Any?,
    @SerializedName("IsApplied") val isApplied: Boolean?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class OrderReviewData(
    @SerializedName("Display") val display: Boolean?,
    @SerializedName("BillingAddress") val billingAddress: Address?,
    @SerializedName("IsShippable") val isShippable: Boolean?,
    @SerializedName("ShippingAddress") val shippingAddress: Address?,
    @SerializedName("SelectedPickupInStore") val selectedPickupInStore: Boolean?,
    @SerializedName("PickupAddress") val pickupAddress: Address?,
    @SerializedName("ShippingMethod") val shippingMethod: Any?,
    @SerializedName("PaymentMethod") val paymentMethod: Any?,
    @SerializedName("CustomValues") val customValues: Map<String, Any>?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)

data class Address(
    @SerializedName("FirstName") val firstName: String?,
    @SerializedName("LastName") val lastName: String?,
    @SerializedName("Email") val email: String?,
    @SerializedName("CompanyEnabled") val companyEnabled: Boolean?,
    @SerializedName("CompanyRequired") val companyRequired: Boolean?,
    @SerializedName("Company") val company: String?,
    @SerializedName("CountryEnabled") val countryEnabled: Boolean?,
    @SerializedName("CountryId") val countryId: Int?,
    @SerializedName("CountryName") val countryName: String?,
    @SerializedName("DefaultCountryId") val defaultCountryId: Int?,
    @SerializedName("StateProvinceEnabled") val stateProvinceEnabled: Boolean?,
    @SerializedName("StateProvinceId") val stateProvinceId: Int?,
    @SerializedName("StateProvinceName") val stateProvinceName: String?,
    @SerializedName("CountyEnabled") val countyEnabled: Boolean?,
    @SerializedName("CountyRequired") val countyRequired: Boolean?,
    @SerializedName("County") val county: String?,
    @SerializedName("CityEnabled") val cityEnabled: Boolean?,
    @SerializedName("CityRequired") val cityRequired: Boolean?,
    @SerializedName("City") val city: String?,
    @SerializedName("StreetAddressEnabled") val streetAddressEnabled: Boolean?,
    @SerializedName("StreetAddressRequired") val streetAddressRequired: Boolean?,
    @SerializedName("Address1") val address1: String?,
    @SerializedName("StreetAddress2Enabled") val streetAddress2Enabled: Boolean?,
    @SerializedName("StreetAddress2Required") val streetAddress2Required: Boolean?,
    @SerializedName("Address2") val address2: String?,
    @SerializedName("ZipPostalCodeEnabled") val zipPostalCodeEnabled: Boolean?,
    @SerializedName("ZipPostalCodeRequired") val zipPostalCodeRequired: Boolean?,
    @SerializedName("ZipPostalCode") val zipPostalCode: String?,
    @SerializedName("PhoneEnabled") val phoneEnabled: Boolean?,
    @SerializedName("PhoneNumber") val phoneNumber: String?,
    @SerializedName("FaxEnabled") val faxEnabled: Boolean?,
    @SerializedName("FaxNumber") val faxNumber: String?,
    @SerializedName("Id") val id: Int?,
    @SerializedName("CustomProperties") val customProperties: Map<String, Any>?
)
