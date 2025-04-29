package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler // Correct import
import android.os.Looper // Needed to work on the main thread if required
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class SplashScreen : AppCompatActivity() {

    private lateinit var uptodown: Animation
    private lateinit var downtoup: Animation
    private lateinit var l1: ImageView
    private lateinit var l2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        l1 = findViewById(R.id.moptra_icon)
        l2 = findViewById(R.id.moptra_splash_screen_textview)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        downtoup = AnimationUtils.loadAnimation(this,R.anim.down_to_up)
        uptodown = AnimationUtils.loadAnimation(

            this, R.anim.up_to_down)
        l1.startAnimation(uptodown)
        l2.startAnimation(downtoup)

        



        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}