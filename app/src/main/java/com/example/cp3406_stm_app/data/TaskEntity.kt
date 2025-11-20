package com.example.cp3406_stm_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)