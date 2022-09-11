package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class GetCategoriesResponse(
    @SerializedName("data")
    val `data`: List<DataForCategory>?,
    @SerializedName("success")
    val success: Boolean?
)