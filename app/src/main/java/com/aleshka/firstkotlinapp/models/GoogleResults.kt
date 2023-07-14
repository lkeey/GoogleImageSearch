package com.aleshka.firstkotlinapp.models

import com.google.gson.annotations.SerializedName

class GoogleResults(
    @field:SerializedName("items") var items: List<GoogleItem>
)