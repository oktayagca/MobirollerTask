package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("itemsCount")
    val itemsCount: Int?,
    @SerializedName("queryCount")
    val queryCount: Int?
)