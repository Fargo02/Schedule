package com.practicum.mymovies.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "schedule_table")
data class ScheduleEntity(
    @PrimaryKey
    val yandexCode: String,
    val esrCode: String,
    val direction: String,
    val stationType: String,
    @ColumnInfo(name = "station_name")
    val title: String,
    val transportType: String
)
