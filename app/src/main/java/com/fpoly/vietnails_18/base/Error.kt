package com.matech.stickers.data.base

import com.google.gson.annotations.SerializedName

class Error(
    @field: SerializedName("itemname") val name: List<String>? = null
)