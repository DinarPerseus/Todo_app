package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!  // Use safe binding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var rvProducts: RecyclerView
    private lateinit var addToCartbtn: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Initialize RecyclerView
        //addToCartbtn=view.findViewById(R.id.addToCart)
        rvProducts = view.findViewById(R.id.rvProductItems)
        rvProducts.layoutManager = LinearLayoutManager(requireContext())

        getData()


        return view
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Fetch the data
                val response = RetrofitInstance.api.getProducts()

                withContext(Dispatchers.Main) {
                    // Check if the response Data is not null
                    if (!response.data.isNullOrEmpty()) {

                        productAdapter = ProductAdapter(response.data)
                        rvProducts.adapter = productAdapter
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
        _binding = null  // Avoid memory leaks
    }
}
