package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.OngoingAdapter
import com.example.myapplication.ViewModel.MainViewModel
import com.example.myapplication.ViewModel.MainViewModelFactory
import com.example.myapplication.Repository.MainRepository
import com.example.myapplication.databinding.FragmentHomefBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class homef : Fragment() {

    private var _binding: FragmentHomefBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomefBinding.inflate(inflater, container, false)

        // Create repository and ViewModel using the factory
        val repository = MainRepository()
        val factory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter with click listener
        val ongoingAdapter = OngoingAdapter(mainViewModel.item) { position ->
            val intent = when (position) {
                0 -> Intent(requireContext(), applyLeave::class.java)
                1 -> Intent(requireContext(), LeaveActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }

        binding.quickLaunchRecyclerView.apply {
            adapter = ongoingAdapter
            layoutManager = GridLayoutManager(requireContext(), getGridColumns())
        }

        updateLoginTime()
        observeAnnouncement() // Observe LiveData for announcement
        mainViewModel.loadAnnouncement() // Trigger loading of announcement
    }

    private fun getGridColumns(): Int {
        return if (resources.configuration.screenWidthDp > 600) 3 else 2
    }

    private fun updateLoginTime() {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val currentDateTime = sdf.format(Date())
        binding.loginTime.text = "Logged in at: $currentDateTime"
    }

    private fun observeAnnouncement() {
        mainViewModel.announcement.observe(viewLifecycleOwner) { announcement ->
            binding.announcementText.text = announcement
            binding.announcementText.isSelected = true // Required for marquee
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
