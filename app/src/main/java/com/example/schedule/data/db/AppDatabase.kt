package com.example.schedule.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.mymovies.data.db.dao.ScheduleDao
import com.practicum.mymovies.data.db.entity.ScheduleEntity

@Database(version = 1, entities = [ScheduleEntity::class])
abstract class AppDatabase : RoomDatabase(){

    abstract fun scheduleDao(): ScheduleDao
}