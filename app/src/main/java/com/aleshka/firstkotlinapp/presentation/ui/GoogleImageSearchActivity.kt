package com.aleshka.firstkotlinapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx. recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.aleshka.firstkotlinapp.R
import com.aleshka.firstkotlinapp.api.GoogleApi
import com.aleshka.firstkotlinapp.api.GoogleService
import com.aleshka.firstkotlinapp.models.GoogleResults
import com.aleshka.firstkotlinapp.presentation.adapters.GoogleImageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class GoogleImageSearchActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "GoogleSearchActivity"
    }

    private lateinit var userInput: EditText
    private lateinit var btnSearch: FloatingActionButton
    private lateinit var imgGoogle: ImageView
    private lateinit var recyclerImg: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_image_search)

        init()

        setListeners()

    }

    private fun init() {
        userInput = findViewById(R.id.userInput)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerImg = findViewById(R.id.recyclerImages)
    }

    private fun setListeners() {
        btnSearch.setOnClickListener {
            search(userInput.text.toString())
        }
    }

    private fun search(text: String) {

        val serviceGoogle: GoogleService = GoogleApi.instance!!.create(GoogleService::class.java)

        val call: Call<GoogleResults?>? = serviceGoogle.find(
            "AIzaSyDdBPCVzYyCmtFtZSSihqOSUsPZglM5x3E",
             "42a504d9a5afa4755",
            text,
            "json",
            "image"

        )

        call!!.enqueue(object : Callback<GoogleResults?> {
            override fun onResponse(
                call: Call<GoogleResults?>,
                response: Response<GoogleResults?>
            ) {
                Log.i(TAG, "success - ${response.code()} - ${response.body()?.items?.get(0)?.image?.url}")

                if (response.isSuccessful) {

                    try {

                        val googleRes: GoogleResults? = response.body()

                        recyclerImg.layoutManager =
                            LinearLayoutManager(this@GoogleImageSearchActivity)
                        recyclerImg.adapter = googleRes?.items?.let { GoogleImageAdapter(it) }

                    } catch (ex: Exception) {
                        Log.i(TAG, "exception - ${ex.stackTraceToString()}")
                    }

//                    try {
//                        val url = URL(googleRes?.items?.get(0)?.image?.url)
//
//                        val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//
//                        val scaledBmp: Bitmap = Bitmap.createScaledBitmap(bmp, IMG_WIDTH, IMG_HEIGHT, true)
//
//                        imgGoogle.setImageBitmap(scaledBmp)
//
//                        setImgInBackground(googleRes?.items?.get(0)?.image?.url.toString())
//
//                        Log.i(TAG, "bmp successfully set")
//
//                    } catch (ex: Exception) {
//                        Log.i(TAG, "exception - ${ex.stackTraceToString()}")
//                    }
                }
            }

            override fun onFailure(call: Call<GoogleResults?>, t: Throwable) {
                Log.i(TAG, "error - ${t.message}")
            }
        })

    }

    private fun setImg(url: String) {
        // in UI thread
        imgGoogle.load(url) {
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_launcher_background)
            memoryCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(40f))
        }
    }
    private fun setImgInBackground(url: String) {
        // in Background
        val request = ImageRequest.Builder(applicationContext)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
            .target(imgGoogle)
            .crossfade(1000)
            .crossfade(true)
            .transformations(RoundedCornersTransformation(40f))
            .build()

        GlobalScope.launch {
            imageLoader.execute(request)
        }

    }

}
