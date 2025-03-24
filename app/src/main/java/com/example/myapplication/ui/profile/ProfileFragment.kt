package com.example.myapplication.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Login
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnLogout = binding.btnlogout
        val btnEditProfile = binding.btneditprofile
        val imageView: ImageView = binding.imageView




        val animZoomInOut: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in_out)
        auth = FirebaseAuth.getInstance()
        imageView.setOnTouchListener{ _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Image pressed
                    imageView.startAnimation(animZoomInOut)
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Image released or touch canceled
                    true
                }
                else -> false
            }
        }
        val usersCollection = firestore.collection("users").document(auth.currentUser?.uid.toString())


        usersCollection.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val firstName = documentSnapshot.getString("firstName")
                val lastName = documentSnapshot.getString("lastName")
                val phone = documentSnapshot.getString("phone")
                val email = documentSnapshot.getString("Email")
                val address = documentSnapshot.getString("address")
                val profession = documentSnapshot.getString("profession")
                //val imageUrl = documentSnapshot.getString("imageUrl")
                binding.tvName.text = firstName + " " + lastName
                binding.tvPhone.text = phone
                binding.tvEmail.text = email
                binding.tvAddress.text = address
                binding.tvProfession.text = profession
                //Glide.with(requireContext()).load(imageUrl).into(imageView)
            } else {
                Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show()

            }

        btnLogout.setOnClickListener {
            // Handle logout button click
            auth.signOut()
            val intent = Intent(requireContext(), MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()

        }

        btnEditProfile.setOnClickListener{
            findNavController().navigate(R.id.navigation_edit_profile)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}