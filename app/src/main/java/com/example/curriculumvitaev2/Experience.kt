package com.example.curriculumvitaev2

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "experiences")
data class Experience(
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    var ExpPic: String,

    val ExpName: String,
    val ExpLoc: String,
    val ExpFirstDate: String,
    val ExpLastDate: String,

    )

