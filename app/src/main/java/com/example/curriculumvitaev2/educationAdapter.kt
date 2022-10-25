package com.example.curriculumvitaev2

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.curriculumvitaev2.utils.AppDataBase

class educationAdapter(val EducationList : MutableList<Education>) : RecyclerView.Adapter<educationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): educationViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.education_single_item,parent,false)
        return educationViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: educationViewHolder, position: Int) {

        holder.EduPic.setImageURI(Uri.parse(EducationList[position].EduPic))
        holder.EduName.setText(EducationList[position].EduName)
        holder.EduLoc.setText(EducationList[position].EduLoc)
        holder.EduFirstDate.setText(EducationList[position].EduFirstDate)
        holder.EduLastdate.setText(EducationList[position].EduLastDate)
        holder.delete.setOnClickListener {
            val builder1: AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)
            builder1.setTitle("Delete Education")
            builder1.setMessage("Are you sure you want to delete the University"+holder.EduName.text+"?")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog, id ->

                    AppDataBase.getDatabase(holder.itemView.context).eduDao().delete( EducationList[position])
                    EducationList.removeAt(position)
                    notifyDataSetChanged()
                    dialog.cancel()})

            builder1.setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

            val alert11: AlertDialog = builder1.create()
            alert11.show()



        }
    }

    override fun getItemCount(): Int =EducationList.size
}