package com.example.hellotoast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/** Task 3: Preserve Count (via ViewModel) **/
class MainViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    init {
        _count.value = 0
    }

    /** Count Up Method **/
    fun countUp() {
        _count.value = (count.value)!!.plus(1)
    }

    override fun onCleared() {
        super.onCleared()
    }

}
