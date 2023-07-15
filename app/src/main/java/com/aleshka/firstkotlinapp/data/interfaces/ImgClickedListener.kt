package com.aleshka.firstkotlinapp.data.interfaces

import com.aleshka.firstkotlinapp.data.models.GoogleItem

interface ImgClickedListener {
    fun onItemClicked(item: GoogleItem?)
}