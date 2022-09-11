package com.example.caseshopiroller.data.remote

import com.example.caseshopiroller.data.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: NetworkApiService
): BaseDataSource() {

    suspend fun getCategories() = getResult {
        apiService.getCategories()
    }

    suspend fun getProductsForAnCategory(options: Map<String, String>) = getResult {
        apiService.getProductsForAnCategory(options)
    }

    suspend fun getSingleProductDetail(id:String) = getResult {
        apiService.getSingleProductDetail(id)
    }
}