package com.fpoly.vietnails_18

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoroutineViewModelFactory(val fakeRepository: FakeRepository) : ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CoroutineViewModel::class.java)) {
            return CoroutineViewModel(fakeRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}