package com.example.caseshopiroller.ui.productDetail

import androidx.lifecycle.ViewModel
import com.example.caseshopiroller.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    fun getSingleProductDetail(id:String) =
        repository.getSingleProductDetail(id)

}