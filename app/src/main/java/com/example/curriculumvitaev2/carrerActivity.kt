package com.example.curriculumvitaev2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar

private lateinit var btnEducation: Button
private lateinit var btnExperience: Button
class carrerActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carrer_activity)
        val toolbar: Toolbar = findViewById(R.id.app_bar2)
        setSupportActionBar(toolbar)
        btnExperience = findViewById(R.id.btnExperience)
        btnEducation = findViewById(R.id.btnEducation)
        toolbar.setNavigationOnClickListener(){
            finish()

        }
        btnExperience.setOnClickListener(){
            changeFragment(ExperienceFragment(), "Experience")
        }

        btnEducation.setOnClickListener(){
            changeFragment(EducationFragment(), "Education")
        }
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, ExperienceFragment()).commit()

    }
    private fun changeFragment(fragment: Fragment, name: String) {

        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(name).commit()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carrer,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addExp->{

                val intent = Intent(this,addExperience::class.java)
                startActivityForResult(intent,10001)
            }
            R.id.addEdu->{

                val intent = Intent(this,addEducation::class.java)
                startActivityForResult(intent,10002)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == 10001) && (resultCode == 10001) ){
            changeFragment(ExperienceFragment(), "Experience")
        }
        if ((requestCode == 10002) && (resultCode == 10002) ){
            changeFragment(EducationFragment(), "Education")
        }
    }

}