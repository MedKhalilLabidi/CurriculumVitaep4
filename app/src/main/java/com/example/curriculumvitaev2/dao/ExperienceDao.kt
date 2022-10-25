package com.example.curriculumvitaev2.dao

import androidx.room.*
import com.example.curriculumvitaev2.Experience

@Dao
interface ExperienceDao {
    @Insert
    fun insert(exp: Experience)

    @Update
    fun update(exp: Experience)

    @Delete
    fun delete(exp: Experience)

    @Query("SELECT * FROM experiences")
    fun getAllExp(): List<Experience>
}