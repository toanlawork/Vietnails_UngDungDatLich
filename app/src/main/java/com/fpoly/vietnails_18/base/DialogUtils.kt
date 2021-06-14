package com.fpoly.vietnails_18.base

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    fun showMessage(
            context: Context?,
            title: String? = null,
            message: String? = null,
            textPositive: String? = null,
            positiveListener: (() -> Unit)? = null,
            textNegative: String? = null,
            negativeListener: (() -> Unit)? = null,
            cancelable: Boolean = false,
            canceledOnTouchOutside: Boolean = false
    ): AlertDialog? =
            context?.let {
                AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(textPositive) { _, _ -> positiveListener?.invoke() }
                        .setNegativeButton(textNegative) { _, _ -> negativeListener?.invoke() }
                        .setCancelable(cancelable)
                        .create().apply {
                            setCanceledOnTouchOutside(canceledOnTouchOutside)
                            show()
                        }
            }
}