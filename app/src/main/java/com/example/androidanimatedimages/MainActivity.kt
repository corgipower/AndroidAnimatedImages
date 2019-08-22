package com.example.androidanimatedimages

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val gifDrawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, R.drawable.cat))
            anim_cat.setImageDrawable(gifDrawable)
            (gifDrawable as AnimatedImageDrawable).start()
        }
        */

        val frameDrawable = ContextCompat.getDrawable(this, R.drawable.hammie_animated)
        anim_cat.setImageDrawable(frameDrawable)
        (frameDrawable as AnimationDrawable).start()

    }
}
