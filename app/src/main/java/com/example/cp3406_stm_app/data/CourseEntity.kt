package com.example.cp3406_stm_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")//table,name
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String
)