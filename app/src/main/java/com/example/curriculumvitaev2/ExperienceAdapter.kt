package com.example.curriculumvitaev2

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.curriculumvitaev2.utils.AppDataBase


class ExperienceAdapter(val ExpList : MutableList<Experience>) : RecyclerView.Adapter<ExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.experience_single_item, parent, false)
        return ExperienceViewHolder(rootView)

    }
    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {

        holder.ExpPic.setImageURI(  Uri.parse(ExpList[position].ExpPic))
        holder.ExpName.setText(ExpList[position].ExpName)
        holder.ExpLoc.setText(ExpList[position].ExpLoc)
        holder.ExpFirstDate.setText(ExpList[position].ExpFirstDate)
        holder.ExpLastdate.setText(ExpList[position].ExpLastDate)
        holder.ExpDesc.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum enim non fermentum interdum. Fusce et congue libero, id eleifend nibh. Nunc feugiat lectus in turpis maximus, non fermentum dui tincidunt.")
        holder.delete.setOnClickListener {
            val builder1: AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)
            builder1.setTitle("Delete Experience")
            builder1.setMessage("Are you sure you want to delete this Experience in "+holder.ExpName.text+"?")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog, id ->

                    AppDataBase.getDatabase(holder.itemView.context).expDao().delete(ExpList[position])
                    ExpList.removeAt(position)
                    notifyDataSetChanged()
                    dialog.cancel()})

            builder1.setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

            val alert11: AlertDialog = builder1.create()
            alert11.show()



        }
    }


    override fun getItemCount(): Int = ExpList.size

}