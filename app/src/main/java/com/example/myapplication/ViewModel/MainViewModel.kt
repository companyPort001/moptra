package com.example.myapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    // Your existing data for quick launch
    val item = repository.item

    // LiveData for announcement section
    private val _announcement = MutableLiveData<String>()
    val announcement: LiveData<String> get() = _announcement

    // Call this to load announcement text
    fun loadAnnouncement() {
        _announcement.value = repository.getAnnouncement()
    }
}
