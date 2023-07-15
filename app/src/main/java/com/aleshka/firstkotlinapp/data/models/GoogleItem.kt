package com.aleshka.firstkotlinapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GoogleItem(
    @field:SerializedName("image") var image: ImageGoogle
): Serializable
