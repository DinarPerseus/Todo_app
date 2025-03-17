package com.example.myapplication.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.dashboard.ProductAdapter.ProductViewHolder


class CartAdapter(private var carts: List<Item>?): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImage: ImageView = itemView.findViewById(R.id.cartimageProduct)
        val productTitle: TextView = itemView.findViewById(R.id.cartProductName)
        val productPrice: TextView = itemView.findViewById(R.id.textUnitPrice)
        val productQuantity: TextView = itemView.findViewById(R.id.textQuantity)
        val totalPrice: TextView=itemView.findViewById(R.id.texttotalprice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return carts?.size ?: 0
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val curCart = carts?.get(position)
        holder.apply {
            if (curCart != null) {
                productTitle.text = curCart.productName
            }
            if (curCart != null) {
                productPrice.text = curCart.unitPrice
            }
            if (curCart != null) {
                productQuantity.text = curCart.quantity.toString()
            }
            if (curCart != null) {
                totalPrice.text = curCart.subTotal.toString()
            }
            if (curCart != null) {
                Glide.with(itemView.context).load(curCart.picture?.imageUrl).into(productImage)
            }

        }
    }

}