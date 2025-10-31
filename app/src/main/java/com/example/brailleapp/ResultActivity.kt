package com.example.brailleapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import com.bumptech.glide.Glide

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LUMINOSITY = "extra_luminosity"
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageView = findViewById<ImageView>(R.id.capturedImageView)
        val luminosityText = findViewById<TextView>(R.id.luminosityTextView)
        val backButton = findViewById<Button>(R.id.backButton)


        // Get luminosity from intent
        val luminosity = intent.getDoubleExtra(EXTRA_LUMINOSITY, 0.0)
        luminosityText.text = "Luminosity: ${"%.2f".format(luminosity)}"

        // Get image URI from intent
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)

        if (!imageUriString.isNullOrEmpty()) {
            val imageUri = Uri.parse(imageUriString)
            // Load image from URI
            Glide.with(this)
                .load(imageUri)
                .into(imageView)

        }

        backButton.setOnClickListener {
            finish() // Go back to camera
        }
    }
}