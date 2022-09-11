package com.example.caseshopiroller.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caseshopiroller.data.entity.DataProductsForAnCategory
import com.example.caseshopiroller.databinding.ProductsItemBinding
import com.example.caseshopiroller.utils.loadImagesWithGlide
import com.example.caseshopiroller.utils.show

class ProductsRecyclerViewAdapter: RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsViewHolder>() {

    private lateinit var binding: ProductsItemBinding
    private var items = listOf<DataProductsForAnCategory>()
    private lateinit var onClickListener: IProductsItemClickListener

    class ProductsViewHolder(private val binding: ProductsItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DataProductsForAnCategory, onClickListener: IProductsItemClickListener) {
            binding.apply {
                imageView.loadImagesWithGlide(item.images!![0].n)
                titleTextView.text = item.title
                priceTextView.text = item.price.toString()
                if(item.useFixPrice == true){
                    discountedPriceTextView.show()
                    discountedPriceTextView.text = item.shippingPrice.toString()
                }
                rootLayout.setOnClickListener {
                    onClickListener.onClick(item)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items:  List<DataProductsForAnCategory>, onClickListener: IProductsItemClickListener) {
        this.items = items
        this.onClickListener = onClickListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = items.size

}