package com.example.myapplication.Activity

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.content.ContentResolver
import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDocfBinding
import com.example.myapplication.databinding.DialogDocumentBinding

class DocfFragment : Fragment(R.layout.fragment_docf) {

    private var _binding: FragmentDocfBinding? = null
    private val binding get() = _binding!!

    private var dialogBinding: DialogDocumentBinding? = null
//    private val filePickerLauncher =
//        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//            uri?.let {
//                dialogBinding?.tvFilePath?.text = "Selected"
//            }
//        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocfBinding.inflate(inflater, container, false)

        // Handle the click on TextViews to open the dialog
        binding.OnBoarding.setOnClickListener {
            showDocumentDialog("OnBoarding Documents")
        }
        binding.OfferLetter.setOnClickListener {
            showDocumentDialog("Offer Letter")
        }
        binding.Appointment.setOnClickListener {
            showDocumentDialog("Appointment Letter")
        }
        binding.Confirmation.setOnClickListener {
            showDocumentDialog("Confirmation Letter")
        }
        binding.Receving.setOnClickListener {
            showDocumentDialog("Receiving Letter")
        }
        binding.Experice.setOnClickListener {
            showDocumentDialog("Experience Letter")
        }
        binding.Termination.setOnClickListener {
            showDocumentDialog("Termination Letter")
        }

        return binding.root
    }

    // Function to show the dialog
    private fun showDocumentDialog(title: String) {
        // Inflate the dialog layout
        val dialogInflater = LayoutInflater.from(requireContext())
        dialogBinding = DialogDocumentBinding.inflate(dialogInflater)

        dialogBinding?.apply {
            dialogTitle.text = title
            dialogContent.text = "Please upload your file for $title."

            // Handle the upload button click
//            btnUpload.setOnClickListener {
//                filePickerLauncher.launch("*/*") // Allow any type of file
//            }
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding?.root)
            .create()

        dialog.show()

        // Set dialog width to 90% of screen width
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dialogBinding = null
    }
}
