package com.example.caseshopiroller.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caseshopiroller.data.entity.DataForCategory
import com.example.caseshopiroller.databinding.DrawerRecyclerViewItemRowBinding
import com.example.caseshopiroller.utils.loadImagesWithGlide

class DrawableMenuRecyclerViewAdapter : RecyclerView.Adapter<DrawableMenuRecyclerViewAdapter.DrawerMenuViewHolder>(){

    private lateinit var binding: DrawerRecyclerViewItemRowBinding
    private var items = listOf<DataForCategory>()
    private lateinit var onClickListener: IDrawableMenuItemClickListener

    class DrawerMenuViewHolder(private val binding: DrawerRecyclerViewItemRowBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DataForCategory?, onClickListener: IDrawableMenuItemClickListener) {
            binding.apply {
                imageViewIcon.loadImagesWithGlide(item!!.icon)
                textViewName.text = item.name
                rootLayout.setOnClickListener {
                    onClickListener.onClick(item)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items:  List<DataForCategory>, onClickListener: IDrawableMenuItemClickListener) {
        this.items = items
        this.onClickListener = onClickListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerMenuViewHolder {
        binding = DrawerRecyclerViewItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrawerMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerMenuViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = items.size
}