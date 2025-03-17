package com.example.myapplication.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.databinding.FragmentCartBinding
import com.example.myapplication.ui.dashboard.ProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment:Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartAdapter: CartAdapter
    private lateinit var rvCart: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        rvCart = binding.recyclerViewCart
        rvCart.layoutManager= LinearLayoutManager(requireContext())


        getData()



        return view

    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Fetch the data
                val response = RetrofitInstance.api.getCart()

                withContext(Dispatchers.Main) {
                    // Check if the response Data is not null
                    if (!response.data?.cart?.items.isNullOrEmpty()) {
                        // Update the adapter with the products
                        cartAdapter = CartAdapter(response.data?.cart?.items)
                        rvCart.adapter = cartAdapter
                    } else {
                        Log.e("TAG", "No products found or Data is null")
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "API Call Failed: ${e.message}")
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}