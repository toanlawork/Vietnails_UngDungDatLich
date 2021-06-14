package com.fpoly.vietnails_18.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.fpoly.vietnails_18.BR


abstract class BaseRecyclerView<T, ItemDataBinding : ViewDataBinding>(var list: List<T>) : RecyclerView.Adapter<BaseViewHolder<ItemDataBinding>>() {
    private var oldList = listOf<T>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemDataBinding> = BaseViewHolder(createItemBinding(parent)).apply {
        bindFirstTime(itemBinding)
    }

    open fun bindFirstTime(itemBinding: ItemDataBinding) {

    }

    open fun rSubmitList(newList: MutableList<T>) {
        (this.list as MutableList<T>).clear()
        list = newList
        notifyDataSetChanged()
    }

    open val itemVariable = BR.item

    private fun createItemBinding(parent: ViewGroup): ItemDataBinding =
            DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId, parent, false
            )

    fun getOldList() = oldList

    abstract val layoutId: Int

    override fun getItemCount(): Int {
        if (list.isNullOrEmpty()) {
            return 0
        } else {
            return list.size
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemDataBinding>, position: Int) {
        holder.apply {
            itemBinding.setVariable(itemVariable, list[position])
            bindData(itemBinding, adapterPosition)
        }
    }

    open fun bindData(itemBinding: ItemDataBinding, position: Int) {

    }
}