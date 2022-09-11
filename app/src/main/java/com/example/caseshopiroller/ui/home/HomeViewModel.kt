package com.example.caseshopiroller.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caseshopiroller.data.entity.GetCategoriesResponse
import com.example.caseshopiroller.data.entity.GetProductsForAnCategoryResponse
import com.example.caseshopiroller.data.repository.Repository
import com.example.caseshopiroller.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private lateinit var categoryList  : LiveData<Resource<GetCategoriesResponse>>
    private var productList =MutableLiveData<Resource<GetProductsForAnCategoryResponse>>()

    init {
        getCategories()
    }
    fun observeCategories(): LiveData<Resource<GetCategoriesResponse>> {
        if (categoryList.value == null) {
            getCategories()
        }
        return categoryList
    }

    private fun getCategories() {
        categoryList =
            repository.getCategories()
    }

    fun observeProductsForAnCategory(options: Map<String, String>): LiveData<Resource<GetProductsForAnCategoryResponse>> {
        if (productList.value == null) {
            getProductsForAnCategory(options)
        }
        return productList
    }

    fun getProductsForAnCategory(options: Map<String, String>) {
        productList =
            repository.getProductsForAnCategory(options) as MutableLiveData<Resource<GetProductsForAnCategoryResponse>>
    }

}