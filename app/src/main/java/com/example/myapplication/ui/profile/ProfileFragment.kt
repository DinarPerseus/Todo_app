package com.example.myapplication.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.bumptech.glide.Glide
import com.example.myapplication.Login
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()
    //private lateinit var profilePreferences: ProfilePreferences



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

        //observeUserData()
        getUserDatafromFirebase()







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

    private fun getUserDatafromFirebase() {

        val usersCollection = firestore.collection("users").document(auth.currentUser?.uid.toString())


        usersCollection.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val firstName = documentSnapshot.getString("firstName")
                val lastName = documentSnapshot.getString("lastName")
                val phone = documentSnapshot.getString("phone")
                val email = documentSnapshot.getString("email")
                val address = documentSnapshot.getString("address")
                val profession = documentSnapshot.getString("profession")
                //val imageUrl = documentSnapshot.getString("imageUrl")
                binding.tvName.text = firstName + " " + lastName
                binding.tvPhone.text = phone
                binding.tvEmail.text = email
                binding.tvAddress.text = address
                binding.tvProfession.text = profession
                //Glide.with(requireContext()).load(imageUrl).into(imageView)
//
//                val userInfo = UserInfo(firstName, phone, email, address, profession)
//                CoroutineScope(Dispatchers.Main).launch {
//                    profilePreferences.updateUserInfo(userInfo)
//                }

            } else {
                binding.tvEmail.text = auth.currentUser?.email
                Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                binding.tvEmail.text = auth.currentUser?.email
                Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show()

            }
    }
//
//    private fun observeUserData() {
//
//
//
//        profilePreferences = ProfilePreferences(requireContext())
//        CoroutineScope(Dispatchers.Main).launch {
//            profilePreferences.getUserInfo().collect { userInfo ->
//                if(userInfo.Name == "User Name" || userInfo.phone == "Phone Number" || userInfo.email == "Email" || userInfo.address == "Address" || userInfo.profession == "Profession"){
//                    getUserDatafromFirebase()
//                    return@collect
//                }
//                Log.d("ProfileFragment", "UserInfo: $userInfo")
//                binding.tvName.text = userInfo.Name
//                binding.tvPhone.text = userInfo.phone
//                binding.tvEmail.text = userInfo.email
//                binding.tvAddress.text = userInfo.address
//                binding.tvProfession.text = userInfo.profession
//                //Glide.with(requireContext()).load(imageUrl).into(imageView)
//
//
//            }
//
//        }
//
//
//    }
//
//    private fun saveUserData() {
//
//
//        val tvName = binding.tvName
//        val tvPhone = binding.tvPhone
//        val tvEmail = binding.tvEmail
//        val tvAddress = binding.tvAddress
//        val tvProfession = binding.tvProfession
//        val userInfo = UserInfo(tvName.text.toString(), tvPhone.text.toString(), tvEmail.text.toString(), tvAddress.text.toString(), tvProfession.text.toString())
//        CoroutineScope(Dispatchers.Main).launch {
//            profilePreferences.updateUserInfo(userInfo)
//        }
//
//    }

    override fun onStop() {
        super.onStop()
       // saveUserData()
    }

    override fun onDestroyView() {
        super.onDestroyView()


        _binding = null
    }
}