package com.example.cp3406_stm_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406_stm_app.data.AppDatabase
import com.example.cp3406_stm_app.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(app: Application, repository: TaskRepository? = null) : AndroidViewModel(app) {

    private val dao = AppDatabase.getInstance(app).taskDao()
    private val repository = repository ?: TaskRepository(dao)

    val tasks = this.repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val progress = tasks.map { list ->
        if (list.isEmpty()) 0f
        else list.count { it.completed }.toFloat() / list.size
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    fun addTask(name: String) {
        viewModelScope.launch {
            repository.addTask(name)
        }
    }

    fun toggleTaskCompleted(id: Int, completed: Boolean) {
        viewModelScope.launch {
            repository.setTaskCompleted(id, completed)
        }
    }


    fun cleanExpired() {
        viewModelScope.launch {
            repository.deleteExpiredTasks()
        }
    }
    val progressValue = progress.map { it * 100 }  // Percentage value
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    val progressDegrees = progress.map { it * 360 }  // Return the Angle directly
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)
}
