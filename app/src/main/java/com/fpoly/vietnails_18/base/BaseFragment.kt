package com.fpoly.vietnails_18.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.fpoly.vietnails_18.R

abstract class BaseFragment<ViewBinding : ViewDataBinding> : Fragment() {
    lateinit var viewBinding: ViewBinding

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.root.isClickable = true
    }

    abstract fun onActivityCreated()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onActivityCreated()
    }

    fun addFragment(
            container: Int = R.id.main_activity_container,
            fragment: Fragment,
            TAG: String?,
            addToBackStack: Boolean = false,
            transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setTransActionAnimation(this, transit)
            add(container, fragment, TAG)
            commitTransaction(this, addToBackStack)
        }
    }

    fun replaceFragment(
            container: Int = R.id.main_activity_container,
            fragment: Fragment,
            TAG: String?,
            addToBackStack: Boolean = false,
            transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setTransActionAnimation(this, transit)
            replace(container, fragment, TAG)
            commitTransaction(this, addToBackStack)
        }
    }

    fun setTransActionAnimation(
            fragmentTransaction: FragmentTransaction,
            transitionFrom: TransitionFrom
    ) {
        fragmentTransaction.apply {
            when (transitionFrom) {
                TransitionFrom.BOTTOM -> setCustomAnimations(
                        R.anim.enter_from_bottom,
                        R.anim.exit_to_bottom,
                        R.anim.enter_from_bottom,
                        R.anim.exit_to_bottom
                )
                TransitionFrom.RIGHT -> setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_right,
                        R.anim.enter_from_right,
                        R.anim.exit_to_right
                )
                TransitionFrom.NONE -> { // do nothing
                }
                else -> { // do nothing
                }
            }
        }
    }

    private fun commitTransaction(
            transaction: FragmentTransaction,
            addToBackStack: Boolean = false
    ) {
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    enum class TransitionFrom {
        RIGHT, BOTTOM, LEFT, TOP, NONE
    }
}