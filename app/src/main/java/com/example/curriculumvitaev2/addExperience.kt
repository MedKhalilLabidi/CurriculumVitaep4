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


class addExperience: AppCompatActivity() {
    var expName : TextView?=null
    var oexpName: TextInputLayout?=null
    var expLocation: TextView?=null
    var oexpLocation : TextInputLayout?=null
    var expDateS : TextInputEditText?=null
    var oexpDateS : TextInputLayout?=null
    var expDateE : TextInputEditText?=null
    var oexpDateE : TextInputLayout?=null
    var saveB : Button?=null

    val pickImage1 = 100
    var imageUri2: Uri? = null
    private lateinit var imageview: ImageView
    private lateinit var dataBase1 : AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_experience)
        expName = findViewById<TextView>(R.id.expNameInput)
        oexpName = findViewById<TextInputLayout>(R.id.outlined_expname)
        expLocation = findViewById<TextView>(R.id.expAdresseInput)
        oexpLocation = findViewById<TextInputLayout>(R.id.outlined_expadresse)
        expDateS = findViewById<TextInputEditText>(R.id.dateSInput)
       oexpDateS = findViewById<TextInputLayout>(R.id.outlined_dateS)
        expDateE = findViewById<TextInputEditText>(R.id.dateEInput)
        oexpDateE = findViewById<TextInputLayout>(R.id.outlined_dateE)

        imageview = findViewById(R.id.expimage)
        val toolbar: Toolbar = findViewById(R.id.app_bar2)
        saveB = findViewById<Button>(R.id.SaveExp)
        setSupportActionBar(toolbar)
toolbar.setTitle("Add Experience")


        expDateS!!.setOnClickListener(){
showDatePickerDialog()

        }
        expDateE!!.setOnClickListener(){
            showDatePickerDialog2()

        }
        toolbar.setNavigationOnClickListener(){
            finish()

        }
        imageview.setOnClickListener {
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage1)

        }

        dataBase1= AppDataBase.getDatabase(this)
        saveB!!.setOnClickListener {
            if (check()) {
               var experience = Experience( ExpPic = imageUri2.toString(), ExpName = expName!!.text.toString(), ExpLoc =  expLocation!!.text.toString(),
               ExpFirstDate = expDateS!!.text.toString(), ExpLastDate = expDateE!!.text.toString())

                addToDataBase(experience)
                setResult(10001)
                finish()
            }

            }


    }

    private fun showDatePickerDialog() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Start date")
                .build()
datePicker.addOnPositiveButtonClickListener {
    expDateS!!.setText(""+datePicker.headerText)
}
        datePicker.show(supportFragmentManager, "tag");
    }
    private fun showDatePickerDialog2() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select End date")
                .build()
        datePicker.addOnPositiveButtonClickListener {
            expDateE!!.setText(""+datePicker.headerText)
        }
        datePicker.show(supportFragmentManager, "tag");
    }

    fun check(): Boolean {
        var c1 = false
        var c2 = false;
        var
                c3 = false
        if (expName!!.text.isEmpty()) {
            oexpName?.error = "Must not be empty!"

        } else {
            oexpName?.error = null
            c1 = true
        }
        if (expLocation!!.text.isEmpty()) {
            oexpLocation?.error = "Must not be empty!"

        }  else {
            oexpLocation?.error = null
            c2 = true;

        }
        if (expDateS!!.text!!.isEmpty()) {
            oexpDateS?.error = "Must not be empty!"
            c3 = false
        } else {
            oexpDateS?.error = null
            c3 = true;
        }
        if (expDateE!!.text!!.isEmpty()) {
            oexpDateE?.error = "Must not be empty!"
            c3 = false

        } else {
            oexpDateE?.error = null
            c3 = true;
        }

        if (imageUri2==null) {
            Toast.makeText(this, "select an image !", Toast.LENGTH_SHORT).show()
        }
        if (c1 && c2 && c3 && imageUri2!=null) {
            return true
        } else return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage1) {
            imageUri2 = data?.data
            getContentResolver().takePersistableUriPermission(imageUri2!!, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageview.setImageURI(imageUri2)
        }
    }
    fun addToDataBase(exp: Experience){

        try {
            dataBase1.expDao().insert(exp!!)


        }catch (ex: Exception){
            Toast.makeText(this, "Could not add the Experience !",Toast.LENGTH_SHORT).show()
            println(ex)
        }

    }

}