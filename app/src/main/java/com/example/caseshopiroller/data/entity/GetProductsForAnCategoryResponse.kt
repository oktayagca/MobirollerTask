package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class GetProductsForAnCategoryResponse(
    @SerializedName("data")
    val `data`: List<DataProductsForAnCategory>?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("success")
    val success: Boolean?
)