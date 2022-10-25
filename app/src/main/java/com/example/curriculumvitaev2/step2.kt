package com.example.curriculumvitaev2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.SeekBar


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox

private var seekBarAndroid: SeekBar? = null
private var seekBarIos: SeekBar? = null
private var seekBarFlutter: SeekBar? = null
private var btnSubmit: Button? = null
private var arabic: CheckBox? = null
private var french: CheckBox? = null
private var english: CheckBox? = null
private var music: CheckBox? = null
private var sport: CheckBox? = null
private var games: CheckBox? = null
private var rme: CheckBox? = null
lateinit var name1 :String
lateinit var age1 :String
    lateinit var mail1 :String
lateinit var gender1 :String

lateinit var imageURI1:String
lateinit var language :String
lateinit var hobbies :String
class step2 :AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.step2)
        seekBarAndroid = findViewById<SeekBar>(R.id.seekBar_android)
        seekBarIos = findViewById<SeekBar>(R.id.seekBar_ios)
        seekBarFlutter= findViewById<SeekBar>(R.id.seekBar_flutter)
         arabic = findViewById<CheckBox>(R.id.checkBox_arabic)
         french = findViewById<CheckBox>(R.id.checkbox_french)
         english = findViewById<CheckBox>(R.id.checkBox_english)
         music = findViewById<CheckBox>(R.id.checkBox_music)
         sport = findViewById<CheckBox>(R.id.checkBox_sport)
         games = findViewById<CheckBox>(R.id.checkBox_game)
        rme = findViewById<CheckBox>(R.id.checkBox_rme)
         btnSubmit= findViewById<Button>(R.id.SUBMIT)
        sharedPreferences = this.getSharedPreferences("savefile", Context.MODE_PRIVATE)

        val intent = intent
         name1 = intent.getStringExtra("Name")!!
         age1 = intent.getStringExtra("Age")!!
        mail1 = intent.getStringExtra("Email").toString()
         gender1 = intent.getStringExtra("Gender")!!
        var bundle :Bundle ?=intent.extras
        imageURI1= intent.getStringExtra("image")!!
        btnSubmit!!.setOnClickListener {



            val intent = Intent(this, cv_result::class.java)



             language = when {
                arabic!!.isChecked && french!!.isChecked && english!!.isChecked -> "Arabic French English"
                arabic!!.isChecked && french!!.isChecked -> "Arabic French"
                arabic!!.isChecked && english!!.isChecked -> "Arabic English"
                french!!.isChecked && english!!.isChecked -> "French English"
                arabic!!.isChecked -> "Arabic"
                french!!.isChecked -> "French"
                english!!.isChecked -> "English"

                else -> "None"
            }


             hobbies = when {
                music!!.isChecked && sport!!.isChecked && games!!.isChecked -> "Music Sport Games"
                  sport!!.isChecked && games!!.isChecked -> "Sport Games"
                sport!!.isChecked && music!!.isChecked -> "Sport Music"
                 games!!.isChecked && music!!.isChecked -> "Games Music"

                music!!.isChecked -> "Music"
                sport!!.isChecked -> "Sport"
                games!!.isChecked -> "Games"

                else -> "None"
            }

doLogin()
            startActivity(intent)
            finish()
        }

    }
    private fun doLogin(){


                //TODO 4 "Edit the SharedPreferences by putting all the data"

                var editor = sharedPreferences.edit()
        editor.clear()
                editor.putString("Name", name1)
                editor.putString("Age",age1)
                editor.putString("Email",mail1)
                editor.putString("Gender",gender1)
                editor.putString("Language",language)
                editor.putString("Hobbies",hobbies)
                editor.putString("image", imageURI1)
                editor.putFloat("Android",seekBarAndroid!!.progress.toFloat())
                editor.putFloat("iOS", seekBarIos!!.progress.toFloat())
                editor.putFloat("Flutter", seekBarFlutter!!.progress.toFloat())
        if (rme!!.isChecked){
                editor.putBoolean("isRemebred",true)}
                editor.commit()

            }
    }

