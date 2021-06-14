package com.fpoly.vietnails_18

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fpoly.vietnails_18.base.BaseFragmentWithViewModel
import com.fpoly.vietnails_18.data.remote.AppAPI
import com.fpoly.vietnails_18.data.remote.NetworkModule
import com.fpoly.vietnails_18.databinding.FragmentCouroutineBinding

class CoroutineFragment : BaseFragmentWithViewModel<FragmentCouroutineBinding, CoroutineViewModel>() {
    val TAG = "VinhNT38"
    private lateinit var fakeRepository: FakeRepository
    override val layoutId: Int
        get() = R.layout.fragment_couroutine
    override val viewModel: CoroutineViewModel
        get() = ViewModelProvider(this, CoroutineViewModelFactory(fakeRepository)).get(CoroutineViewModel::class.java)

    override fun onActivityCreated() {
        viewModel.apply {
            getData()
            exposeData().observe(viewLifecycleOwner, Observer { it ->
                viewBinding.textView.text = it.toString()
            })
        }
    }

    override fun initRepository() {
        val appAPI = NetworkModule.provideAPI().create(AppAPI::class.java)
        fakeRepository = FakeRepositoryImpl(appAPI)
    }
}