package com.example.curriculumvitaev2

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "educations")
data class Education(
    @PrimaryKey(autoGenerate = true)
    val id:Int?= null,
    val EduPic : String,
    val EduName : String,
    val EduLoc : String,
    val EduFirstDate : String,
    val EduLastDate: String,
)