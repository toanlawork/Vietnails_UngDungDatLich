package com.fpoly.vietnails_18.ui.main

import android.os.Bundle
import com.fpoly.vietnails_18.CoroutineFragment
import com.fpoly.vietnails_18.R
import com.fpoly.vietnails_18.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(R.id.main_activity_container,
                CoroutineFragment(),
                "LoginFragment",
                true)
    }
}