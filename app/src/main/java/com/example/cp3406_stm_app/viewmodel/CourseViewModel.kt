package com.example.cp3406_stm_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406_stm_app.data.AppDatabase
import com.example.cp3406_stm_app.data.CourseEntity
import com.example.cp3406_stm_app.repository.CourseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CourseViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.getInstance(app).courseDao()
    private val repository = CourseRepository(dao)

    val courses = repository.allCourses.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addCourse(name: String, day: String, start: String, end: String) {
        viewModelScope.launch {
            repository.addCourse(name, day, start, end)
        }
    }

    fun deleteCourse(course: CourseEntity) {
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
    }
}