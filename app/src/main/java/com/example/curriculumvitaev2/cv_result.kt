package com.example.curriculumvitaev2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.example.curriculumvitaev2.utils.AppDataBase
import java.net.URI

class cv_result: AppCompatActivity(){

    private lateinit var btnSkills: Button
    private lateinit var btnHobbies: Button
    private lateinit var btnLanguages: Button
    private lateinit var btnCareer: Button
private lateinit var fragmentskills:skillsfragment
    private lateinit var fragmenthobbies:hobbiesfragment
    private lateinit var fragmentlanguage:languagefragment
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var sharedPreferences: SharedPreferences
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cv_result)

        supportActionBar?.hide()
        btnSkills = findViewById(R.id.btnSkills)

        btnHobbies = findViewById(R.id.btnHobbies)
        btnLanguages = findViewById(R.id.btnLanguages)

        val imageView:ImageView = findViewById(R.id.pf2)

        val tname : TextView = findViewById(R.id.tname)


        val temail : TextView = findViewById(R.id.temail)
        sharedPreferences = this.getSharedPreferences("savefile", Context.MODE_PRIVATE)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

            var imageUri = Uri.parse(sharedPreferences.getString("image",""))
            println("--------------------------------------"+imageUri)
        imageView.setImageURI(null);
            imageView.setImageURI(imageUri)
            tname.setText(   sharedPreferences.getString("Name",""))
            temail.setText(sharedPreferences.getString("Email",""))
            var android = sharedPreferences.getFloat("Android",0f)
            var ios =  sharedPreferences.getFloat("iOS",0f)
            var flutter =  sharedPreferences.getFloat("Flutter",0f)
            var Language= sharedPreferences.getString("Language","")
            var hobbies= sharedPreferences.getString("Hobbies","")
             fragmentskills=skillsfragment.newInstance(android/100,ios/100,flutter/100)
            fragmentlanguage=languagefragment.newInstance(Language.toString(),"")
             fragmenthobbies=hobbiesfragment.newInstance(hobbies.toString(),"")


        toolbar.setNavigationOnClickListener(){
            finish()

        }
        btnSkills.setOnClickListener{
            changeFragment(fragmentskills,"Skills")
        }
        btnHobbies.setOnClickListener{
            changeFragment(fragmenthobbies, "Hobbies")
        }
        btnLanguages.setOnClickListener{
            changeFragment(fragmentlanguage, "Languages")
        }
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragmentskills).commit()
        btnCareer = findViewById(R.id.btnCareer)

        btnCareer.setOnClickListener {
            val intent = Intent(this,carrerActivity::class.java)
            startActivity(intent)
        }

    }
    private fun changeFragment(fragment: Fragment, name: String) {


        if (name.isEmpty())

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

        else

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(name).commit()

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){


            R.id.info -> changeFragment(infofragment(), "info")
            R.id.logout->{
                val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
                builder1.setTitle("Logout")
                builder1.setMessage("Are you sure you want to logout ?")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener { dialog, id ->

                        getSharedPreferences("savefile", MODE_PRIVATE).edit().clear().apply()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)

                        dialog.cancel()})

                builder1.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

                val alert11: AlertDialog = builder1.create()
                alert11.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }}
