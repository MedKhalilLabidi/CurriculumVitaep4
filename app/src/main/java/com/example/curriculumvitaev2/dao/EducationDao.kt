package com.example.curriculumvitaev2.dao

import androidx.room.*
import com.example.curriculumvitaev2.Education
import com.example.curriculumvitaev2.Experience
@Dao
interface EducationDao {
    @Insert
    fun insert(edu: Education)

    @Update
    fun update(edu: Education)

    @Delete
    fun delete(edu: Education)

    @Query("SELECT * FROM educations")
    fun getAllEdu(): List<Education>
}