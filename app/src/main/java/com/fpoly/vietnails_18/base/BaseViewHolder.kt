package com.fpoly.vietnails_18.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<ItemDataBinding : ViewDataBinding>(val itemBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
}