package com.aleshka.firstkotlinapp.models

import com.google.gson.annotations.SerializedName

class ImageGoogle(
    @field:SerializedName("thumbnailLink") var url: String
)
