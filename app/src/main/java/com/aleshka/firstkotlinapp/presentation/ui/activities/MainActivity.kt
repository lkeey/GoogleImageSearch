package com.aleshka.firstkotlinapp.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aleshka.firstkotlinapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ActivityMain"
    }

    private lateinit var btnSend: Button
    private lateinit var btnChange: Button

    private lateinit var userInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        setListeners()
    }

    private fun init() {
        btnSend = findViewById(R.id.btnSend)
        btnChange = findViewById(R.id.btnActivity)
        userInput = findViewById(R.id.userInput)
    }

    private fun setListeners() {
        btnSend.setOnClickListener {
            Log.i(TAG, "send toast - ${userInput.text}")

            sendToast(userInput.text.toString())
        }

        btnChange.setOnClickListener {
            val intent = Intent(this@MainActivity, GoogleImageSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendToast(text: String) {
        Toast.makeText(applicationContext, "Your text - $text", Toast.LENGTH_SHORT).show()
    }
}