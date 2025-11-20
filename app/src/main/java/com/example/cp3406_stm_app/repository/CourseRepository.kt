package com.example.cp3406_stm_app.repository

import com.example.cp3406_stm_app.data.CourseDao
import com.example.cp3406_stm_app.data.CourseEntity
import kotlinx.coroutines.flow.Flow

class CourseRepository(private val dao: CourseDao) {

    val allCourses: Flow<List<CourseEntity>> = dao.getAllCourses()

    suspend fun addCourse(name: String, day: String, start: String, end: String) {
        dao.insertCourse(CourseEntity(name = name, dayOfWeek = day, startTime = start, endTime = end))
    }

    suspend fun deleteCourse(course: CourseEntity) {
        dao.deleteCourse(course)
    }
}