package com.example.caseshopiroller.data.entity


import com.google.gson.annotations.SerializedName

data class DataProductsForAnCategory(
    @SerializedName("appId")
    val appId: String?,
    @SerializedName("brand")
    val brand: Brand?,
    @SerializedName("brandId")
    val brandId: String?,
    @SerializedName("campaignPrice")
    val campaignPrice: Double?,
    @SerializedName("category")
    val category: Category?,
    @SerializedName("categoryId")
    val categoryId: String?,
    @SerializedName("createDate")
    val createDate: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endDate")
    val endDate: String?,
    @SerializedName("featuredImage")
    val featuredImage: FeaturedImage?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("isPublished")
    val isPublished: Boolean?,
    @SerializedName("isUnLimitedStock")
    val isUnLimitedStock: Boolean?,
    @SerializedName("itemType")
    val itemType: String?,
    @SerializedName("maxQuantityPerOrder")
    val maxQuantityPerOrder: Int?,
    @SerializedName("orderIndex")
    val orderIndex: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("publishmentDate")
    val publishmentDate: String?,
    @SerializedName("shippingPrice")
    val shippingPrice: Double?,
    @SerializedName("stock")
    val stock: Int?,
    @SerializedName("stockCode")
    val stockCode: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updateDate")
    val updateDate: String?,
    @SerializedName("useFixPrice")
    val useFixPrice: Boolean?,
    @SerializedName("variantData")
    val variantData: List<Any>?,
    @SerializedName("videos")
    val videos: List<Any>?
)