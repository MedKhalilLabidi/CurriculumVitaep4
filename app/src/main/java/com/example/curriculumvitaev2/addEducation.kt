package com.example.curriculumvitaev2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.curriculumvitaev2.utils.AppDataBase
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class addEducation : AppCompatActivity() {
    lateinit var eduName :TextView
    lateinit var eduAdresse :TextView
    lateinit var eduDateS :TextInputEditText
    lateinit var eduDateE :TextInputEditText
    lateinit var oeduName :TextInputLayout
    lateinit var oeduAdresse :TextInputLayout
    lateinit var oeduDateS :TextInputLayout
    lateinit var oeduDateE :TextInputLayout
    lateinit var imageViewEdu: ImageView
    lateinit var saveEdu: Button
    var imageUriEdu: Uri? = null
    val pickImageEdu = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_education)
        val toolbar: Toolbar = findViewById(R.id.app_bar2)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Add Education")
        eduName = findViewById<TextView>(R.id.eduNameInput)
        oeduName  = findViewById<TextInputLayout>(R.id.outlined_eduname)
        eduAdresse = findViewById<TextView>(R.id.eduAdresseInput)
        oeduAdresse  = findViewById<TextInputLayout>(R.id.outlined_eduadresse)
        eduDateS = findViewById<TextInputEditText>(R.id.edudateSInput)
        oeduDateS  = findViewById<TextInputLayout>(R.id.outlined_edudateS)
        eduDateE = findViewById<TextInputEditText>(R.id.edudateEInput)
        oeduDateE = findViewById<TextInputLayout>(R.id.outlined_edudateE)


        imageViewEdu= findViewById(R.id.eduimage)

        saveEdu = findViewById<Button>(R.id.SaveEdu)
        setSupportActionBar(toolbar)


        eduDateS!!.setOnClickListener(){
            showDatePickerDialog()

        }
        eduDateE!!.setOnClickListener(){
            showDatePickerDialog2()

        }



        imageViewEdu.setOnClickListener {
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImageEdu)

        }


        saveEdu!!.setOnClickListener {
            if (check()) {
                var education = Education( EduPic = imageUriEdu.toString(), EduName = eduName!!.text.toString(), EduLoc =  eduAdresse!!.text.toString(),
                    EduFirstDate = eduDateS!!.text.toString(), EduLastDate = eduDateE!!.text.toString())

                addToDataBase(education )
                setResult(10002)
                finish()
            }

        }



        toolbar.setNavigationOnClickListener(){
            finish()

        }

    }

    private fun showDatePickerDialog() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Start date")
                .build()
        datePicker.addOnPositiveButtonClickListener {
            eduDateS!!.setText(""+datePicker.headerText)
        }
        datePicker.show(supportFragmentManager, "tag");
    }
    private fun showDatePickerDialog2() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select End date")
                .build()
        datePicker.addOnPositiveButtonClickListener {
            eduDateE!!.setText(""+datePicker.headerText)
        }
        datePicker.show(supportFragmentManager, "tag");
    }

    fun check(): Boolean {
        var c1 = false
        var c2 = false;
        var
                c3 = false
        if (eduName!!.text.isEmpty()) {
            oeduName?.error = "Must not be empty!"

        } else {
            oeduName?.error = null
            c1 = true
        }
        if (eduAdresse!!.text.isEmpty()) {
            oeduAdresse?.error = "Must not be empty!"

        }  else {
            oeduAdresse?.error = null
            c2 = true;

        }
        if (eduDateS!!.text!!.isEmpty()) {
            oeduDateS?.error = "Must not be empty!"
            c3 = false
        } else {
            oeduDateS?.error = null
            c3 = true;
        }
        if (eduDateE!!.text!!.isEmpty()) {
            oeduDateE?.error = "Must not be empty!"
            c3 = false

        } else {
            oeduDateE?.error = null
            c3 = true;
        }

        if (imageUriEdu ==null) {
            Toast.makeText(this, "select an image !", Toast.LENGTH_SHORT).show()
        }
        if (c1 && c2 && c3 && imageUriEdu !=null) {
            return true
        } else return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImageEdu) {
            imageUriEdu = data?.data
            getContentResolver().takePersistableUriPermission(imageUriEdu !!, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageViewEdu.setImageURI(imageUriEdu )
        }
    }
    fun addToDataBase(edu:Education){

        try {
         AppDataBase.getDatabase(this).eduDao().insert(edu!!)

        }catch (ex: Exception){
            Toast.makeText(this, "Could not add the Education !", Toast.LENGTH_SHORT).show()
            println(ex)
        }

    }
}