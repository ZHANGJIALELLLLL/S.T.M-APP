package com.example.cp3406_stm_app.repository

import com.example.cp3406_stm_app.data.TaskDao
import com.example.cp3406_stm_app.data.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun addTask(name: String) {
        dao.insertTask(TaskEntity(name = name))
    }

    suspend fun setTaskCompleted(id: Int, completed: Boolean) {
        dao.updateTaskStatus(id, completed)
    }

    suspend fun deleteExpiredTasks() {
        val threshold = System.currentTimeMillis() - 20 * 60 * 60 * 1000 // 20小时
        dao.deleteExpiredTasks(threshold)
    }
}