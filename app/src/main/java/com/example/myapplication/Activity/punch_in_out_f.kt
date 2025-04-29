package com.example.myapplication.Activity

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPunchInOutBinding
import com.google.android.material.snackbar.Snackbar

class PunchInOut : Fragment() {

    private var _binding: FragmentPunchInOutBinding? = null
    private val binding get() = _binding!!

    private var isTimerRunning = false
    private var seconds = 0
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPunchInOutBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.punchBtn.setOnClickListener {
            if (isTimerRunning) {
                showPunchOutConfirmation()
            } else {
                showWorkModeDialog()
            }
        }

        return view
    }

    private fun showWorkModeDialog() {
        val options = arrayOf("Work from Home", "Work from Office")
        AlertDialog.Builder(requireContext())
            .setTitle("Select Work Mode")
            .setItems(options) { _, _ ->
                startTimer()
                changeButtonStrokeColor(R.color.Green) // Change stroke to GREEN on Punch-In
                isTimerRunning = true

                // ✅ Show confirmation Snackbar
                showConfirmationSnackbar("Punch-In Successful")
            }
            .setCancelable(false)
            .show()
    }

    private fun showPunchOutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm Punch-Out")
            .setMessage("Are you sure you want to punch out?")
            .setPositiveButton("Yes") { _, _ ->
                stopTimer()
                changeButtonStrokeColor(R.color.Red) // Change stroke to RED on Punch-Out
                isTimerRunning = false
            }
            .setNegativeButton("No", null) // Do nothing if "No" is clicked
            .show()
    }

    private fun startTimer() {
        runnable = object : Runnable {
            override fun run() {
                seconds++
                binding.timerTextView.text = formatTime(seconds)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable!!)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable!!)
        runnable = null
        seconds = 0
        binding.timerTextView.text = formatTime(seconds)
    }

    private fun formatTime(seconds: Int): String {
        val hrs = seconds / 3600
        val mins = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("%02d:%02d:%02d", hrs, mins, secs)
    }

    private fun changeButtonStrokeColor(colorResId: Int) {
        val newColor = ContextCompat.getColor(requireContext(), colorResId)
        binding.punchBtn.strokeColor = ColorStateList.valueOf(newColor) // ✅ Official MaterialButton method
    }

    @SuppressLint("RestrictedApi")
    private fun showConfirmationSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)

        // Customize Snackbar background color
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Green))

        // Change text color
        val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        // ✅ Add Green Checkmark Icon
        val icon = ImageView(requireContext())
        icon.setImageResource(R.drawable.done_icon) // Create this drawable in res/drawable/
        icon.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

        // Add icon to Snackbar layout
        val snackbarLayout = snackbarView as Snackbar.SnackbarLayout
        snackbarLayout.addView(icon, 0)

        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable!!)
        _binding = null
    }
}
