package com.android.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> get() = _liveData

    fun updateLiveData(value: String) {
        _liveData.value = value
    }
}