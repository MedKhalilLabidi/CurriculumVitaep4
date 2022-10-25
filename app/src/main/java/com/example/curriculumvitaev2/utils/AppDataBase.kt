package com.example.curriculumvitaev2.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.curriculumvitaev2.Education
import com.example.curriculumvitaev2.Experience
import com.example.curriculumvitaev2.dao.EducationDao
import com.example.curriculumvitaev2.dao.ExperienceDao
@Database(entities = [Experience::class,Education::class], version = 6)
abstract class AppDataBase : RoomDatabase(){

    abstract fun expDao(): ExperienceDao
    abstract fun eduDao(): EducationDao
    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "experience")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance!!
        }
    }
}