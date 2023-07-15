package com.aleshka.firstkotlinapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageGoogle(
    @field:SerializedName("thumbnailLink") var url: String
): Serializable
