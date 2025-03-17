package com.example.myapplication.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import retrofit2.http.POST

class ProductAdapter(private var products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.cardImage)
        val productTitle: TextView = itemView.findViewById(R.id.cardTitle)
        val productDescription: TextView = itemView.findViewById(R.id.cardDescription)
        val productButton: Button = itemView.findViewById(R.id.addToCart) // Button view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val curProduct = products[position]

        holder.apply {
            // Set product image using Glide

            Glide.with(itemView.context).load(curProduct.pictureModels[0].imageUrl).into(productImage)
            // Set product title
            productTitle.text = curProduct.name

            // Set product description
            productDescription.text = curProduct.shortDescription

            // Set the "Add to Cart" button text (or use an actual Button instead of TextView)
            productButton.text = itemView.context.getString(R.string.add_to_card)
            productButton.setOnClickListener{
                makeAddToCartRequest(curProduct)
            }
        }
    }


    private fun makeAddToCartRequest(curProduct: Product) {
        // Create the FormValue object
        val formValue = FormValue(key = "addtocart_1.EnteredQuantity", value = "1")

        // Create the AddToCartRequest object
        val addToCartRequest = AddToCartRequest(formValues = listOf(formValue))

        // Now, use Retrofit to make the API call
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = RetrofitInstance.api.addToCart(curProduct.id,addToCartRequest)
                if (response.isSuccessful) {
                    val addToCartResponse = response.body()
                    if (addToCartResponse != null) {
                        // Handle the successful response
                        Log.d("TAG", "Response: $addToCartResponse")
                        val totalCartProducts = addToCartResponse.data?.totalShoppingCartProducts
                        val totalWishlistProducts = addToCartResponse.data?.totalWishListProducts
                        val redirectToDetails = addToCartResponse.data?.redirectToDetailsPage
                        val redirectToWishlist = addToCartResponse.data?.redirectToWishListPage
                        val redirectToCart = addToCartResponse.data?.redirectToShoppingCartPage
                        val message = addToCartResponse.message
                        val errorList = addToCartResponse.errorList
                        Log.d("TAG", "Total Cart Products: $totalCartProducts")
                        Log.d("TAG", "Total Wishlist Products: $totalWishlistProducts")
                        Log.d("TAG", "Redirect to Details: $redirectToDetails")
                        Log.d("TAG", "Redirect to Wishlist: $redirectToWishlist")
                        Log.d("TAG", "Redirect to Cart: $redirectToCart")
                        Log.d("TAG", "Message: $message")
                        Log.d("TAG", "Error List: $errorList")
                    }
                } else {
                    // Handle the error response
                    Log.e("TAG", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e("TAG", "Exception: ${e.message}")
            }
        }
    }
}
