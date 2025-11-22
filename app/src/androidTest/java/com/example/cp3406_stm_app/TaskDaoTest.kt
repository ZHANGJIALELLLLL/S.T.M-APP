package com.example.cp3406_stm_app

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406_stm_app.data.AppDatabase
import com.example.cp3406_stm_app.data.TaskDao
import com.example.cp3406_stm_app.data.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class TaskDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()  // Adding this allows the main thread to query
            .build()
        dao = db.taskDao()//Start the database
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insert_and_read_tasks() = runTest {
        // Use clear types and null checks
        val testTask = TaskEntity(name = "Test Room", createdAt = System.currentTimeMillis())
        dao.insertTask(testTask)
        val list = dao.getAllTasks().first()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("Test Room", list.firstOrNull()?.name)
    }

}