package com.example.myapplication.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.EditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileFragment : Fragment() {

    private var _binding: EditProfileBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle Image Upload Button Click
        binding.btnUploadImage.setOnClickListener {
            openGallery()
        }

        // Handle Submit Button Click
        binding.btnSubmit.setOnClickListener {
            saveProfile()
        }

        // Handle Back Button Click
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed() // Go back to previous fragment/activity
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                binding.ivProfileImage.setImageURI(uri) // Set selected image
            }
        }
    }

    private fun saveProfile() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val phone = binding.etPhoneNumber.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val profession = binding.etProfession.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty() || profession.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val user = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "phone" to phone,
            "Email" to auth.currentUser?.email,
            "address" to address,
            "profession" to profession,
            //"imageUrl" to imageUrl
        )


        firestore.collection("users").document(auth.currentUser?.uid.toString())
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()

            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to save profile!", Toast.LENGTH_SHORT).show()
            }
    }



//    private fun saveUserDataToFirestore(firstName: String, lastName: String, phone: String, address: String, profession: String, imageUrl: String) {
//        val user = hashMapOf(
//            "firstName" to firstName,
//            "lastName" to lastName,
//            "phone" to phone,
//            "address" to address,
//            "profession" to profession,
//            "imageUrl" to imageUrl
//        )
//
//        firestore.collection("users").document(phone)
//            .set(user)
//            .addOnSuccessListener {
//                Toast.makeText(requireContext(), "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(requireContext(), "Failed to save profile!", Toast.LENGTH_SHORT).show()
//            }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }
}
