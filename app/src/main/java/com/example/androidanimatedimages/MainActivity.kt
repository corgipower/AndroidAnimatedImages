package com.example.androidanimatedimages

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drawables = listOf(R.drawable.cat, R.drawable.hammie_animated)
    var pointer = 0
    fun incrementPointer() {
        pointer++
        if(pointer >= drawables.size) {
            pointer = 0
        }
    }

    fun decrementPointer() {
        pointer--
        if(pointer < 0) {
            pointer = drawables.size - 1
        }
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_next.setOnClickListener {
            incrementPointer()
            anim_img.setImageDrawable(ContextCompat.getDrawable(this, drawables[pointer]))
        }

        btn_back.setOnClickListener {
            decrementPointer()
            anim_img.setImageDrawable(ContextCompat.getDrawable(this, drawables[pointer]))
        }

        btn_play.setOnClickListener {view ->
            when(pointer) {
                0 -> animatedGif(drawables[pointer], anim_img)
                1 -> animationDrawable(drawables[pointer], anim_img)
            }
            animateVector(R.drawable.play_to_pause, view as ImageView)
        }
    }

    private fun animateVector(index: Int, view: ImageView) {
        val animatedVectorDrawable = ContextCompat.getDrawable(this, index)
        view.setImageDrawable(animatedVectorDrawable)
        (animatedVectorDrawable as Animatable).start()
    }

    private fun animatedGif(index: Int, view: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val gifDrawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, index))
            view.setImageDrawable(gifDrawable)
            (gifDrawable as AnimatedImageDrawable).start()
        }
    }

    private fun animationDrawable(index: Int, view: ImageView) {
        val frameDrawable = ContextCompat.getDrawable(this, index)
        view.setImageDrawable(frameDrawable)
        (frameDrawable as AnimationDrawable).start()
    }
}
