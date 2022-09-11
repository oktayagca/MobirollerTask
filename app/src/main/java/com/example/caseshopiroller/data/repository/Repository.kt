package com.example.caseshopiroller.data.repository

import com.example.caseshopiroller.data.remote.RemoteDataSource
import com.example.caseshopiroller.data.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getCategories() = performNetworkOperation {
        remoteDataSource.getCategories()
    }

    fun getProductsForAnCategory(options: Map<String, String>) = performNetworkOperation {
        remoteDataSource.getProductsForAnCategory(options)
    }

    fun getSingleProductDetail(id:String) = performNetworkOperation {
        remoteDataSource.getSingleProductDetail(id)
    }
}