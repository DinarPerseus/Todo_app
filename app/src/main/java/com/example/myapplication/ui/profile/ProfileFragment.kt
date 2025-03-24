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
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myapplication.Login
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnLogout = binding.btnlogout
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

        btnLogout.setOnClickListener {
            // Handle logout button click
            auth.signOut()
            val intent = Intent(requireContext(), MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}