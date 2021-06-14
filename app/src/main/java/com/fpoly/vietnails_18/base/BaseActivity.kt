package com.fpoly.vietnails_18.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {
    fun addFragment(
            container: Int,
            fragment: Fragment,
            tag: String,
            isAddBackstack: Boolean = false
    ) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment, tag)
            if (isAddBackstack) {
                addToBackStack(null)
            }
            commit()
        }
    }


    fun replaceFragment(
            container: Int,
            fragment: Fragment,
            tag: String,
            isAddBackstack: Boolean = false
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment, tag)
            if (isAddBackstack) {
                addToBackStack(null)
            }
            commit()
        }
    }
}