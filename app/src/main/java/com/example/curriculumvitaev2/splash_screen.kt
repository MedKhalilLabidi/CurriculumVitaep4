package com.example.curriculumvitaev2
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager

private val SPLASH_TIME_OUT: Long = 2000
class splash_screen :AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        sharedPreferences = this.getSharedPreferences("savefile", Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            if(sharedPreferences.getBoolean("isRemebred",false)){

                startActivity(Intent(this, cv_result::class.java))
            }
            else
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}