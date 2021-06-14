package com.fpoly.vietnails_18

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fpoly.User
import com.fpoly.vietnails_18.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewModel(private val fakeRepository: FakeRepository) : BaseViewModel() {
    private val data = MutableLiveData<List<User>>()
    fun exposeData() = data
    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("VinhNT38", "run on ${Thread.currentThread()}")
            val dataResponse = fakeRepository.getUser()
            withContext(Dispatchers.Main) {
                Log.d("VinhNT38", "Post value on ${Thread.currentThread()}")
                data.value = dataResponse.dataField
            }
        }
    }
}