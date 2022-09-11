package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("createDate")
    val createDate: String?,
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updateDate")
    val updateDate: String?
)