package com.example.caseshopiroller.data.remote

import com.example.caseshopiroller.data.entity.GetCategoriesResponse
import com.example.caseshopiroller.data.entity.GetProductsForAnCategoryResponse
import com.example.caseshopiroller.data.entity.GetSingleProductDetail
import com.example.caseshopiroller.data.utils.getCategories
import com.example.caseshopiroller.data.utils.getProductsForAnCategory
import com.example.caseshopiroller.data.utils.getSingleProductDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface NetworkApiService {
    @GET(getCategories)
    suspend fun getCategories():Response<GetCategoriesResponse>
    @GET(getProductsForAnCategory)
    suspend fun getProductsForAnCategory(@QueryMap(encoded=true) options:Map<String, String>):Response<GetProductsForAnCategoryResponse>
    @GET(getSingleProductDetail)
    suspend fun getSingleProductDetail(@Path("id") id:String): Response<GetSingleProductDetail>
}