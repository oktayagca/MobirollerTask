package com.example.caseshopiroller.ui.productDetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.caseshopiroller.R
import com.example.caseshopiroller.data.entity.DataForSingleProduct
import com.example.caseshopiroller.databinding.FragmentProductDetailBinding
import com.example.caseshopiroller.ui.MainActivity
import com.example.caseshopiroller.ui.base.BaseFragment
import com.example.caseshopiroller.utils.Resource
import com.example.caseshopiroller.utils.loadImagesWithGlide
import com.example.caseshopiroller.utils.showToastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProductDetailBinding =FragmentProductDetailBinding.inflate(inflater!!,container,false)

    override fun onFragmentCreate() {
        super.onFragmentCreate()
        getSingleProductDetail(args.productId)
    }

    private fun getSingleProductDetail(productId:String) {
        viewModel.getSingleProductDetail(productId).observe(this) { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(true)
                }
                Resource.Status.SUCCESS -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(false)
                    initViews(response.data?.data)
                }
                Resource.Status.ERROR -> {
                    (requireActivity() as MainActivity).showLoadingProgressBar(false)
                    showToastMessage(response.message!!)
                }
            }
        }
    }

    private fun initViews(data: DataForSingleProduct?) {
        binding.apply {
            titleTextView.text = data?.title
            priceTextView.text = data?.price.toString()
            if (data?.stock!! >0){
                stockInfoTextView.text = data.stock.toString()
            }else{
                stockInfoTextView.text = getString(R.string.out_of_stock)
            }
            descTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(data.description)
            }
            imageView.loadImagesWithGlide(data.images?.get(0)?.n)
        }
    }
}