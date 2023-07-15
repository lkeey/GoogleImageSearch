package com.aleshka.firstkotlinapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GoogleResults(
    @field:SerializedName("items") var items: List<GoogleItem>
):Serializable