package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class GetSingleProductDetail(
    @SerializedName("data")
    val `data`: DataForSingleProduct?,
    @SerializedName("success")
    val success: Boolean?
)