package com.aleshka.firstkotlinapp.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.aleshka.firstkotlinapp.R
import com.aleshka.firstkotlinapp.data.models.GoogleItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoFragment : BottomSheetDialogFragment() {

    private lateinit var imgGoogle: ImageView
    private lateinit var receivedImg: GoogleItem
    private lateinit var imgUrl: TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_info, container, false)

        receiveData()

        init(view)

        setData(receivedImg.image.url)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun receiveData() {
        val bundle = arguments

        receivedImg = bundle?.getSerializable("img", GoogleItem::class.java)!!
    }

    private fun init(view: View) {
        imgGoogle = view.findViewById(R.id.img)
        imgUrl = view.findViewById(R.id.url)
    }

    private fun setData(url: String) {

        imgGoogle.load(url) {
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_launcher_background)
            memoryCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(40f))
        }

        imgUrl.text = url

    }
}
