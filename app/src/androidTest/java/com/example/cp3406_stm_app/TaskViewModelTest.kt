package com.example.cp3406_stm_app
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406_stm_app.data.AppDatabase
import com.example.cp3406_stm_app.repository.TaskRepository
import com.example.cp3406_stm_app.viewmodel.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var viewModel: TaskViewModel
    // Add a test scheduler
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        // Set up the test scheduler
        Dispatchers.setMain(testDispatcher)

        val app = ApplicationProvider.getApplicationContext() as android.app.Application
        db = Room.inMemoryDatabaseBuilder(app, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        val repository = TaskRepository(db.taskDao())
        viewModel = TaskViewModel(app, repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        db.close()
        // Reset the scheduler
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addTask_updatesList() = runTest {
        viewModel.addTask("Study Kotlin")
        // Wait for all coroutines to complete
        advanceUntilIdle()
        val list = viewModel.tasks.first()
        Assert.assertTrue(list.any { it.name == "Study Kotlin" })
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun progress_calculatesCorrectly() = runTest {
        viewModel.addTask("A")
        viewModel.addTask("B")
        // Wait for the coroutine to complete
        advanceUntilIdle()
        // Get the task list
        val tasks = viewModel.tasks.first()//b
        // Safety inspection: Ensure there are tasks
        Assert.assertTrue("任务列表不应为空", tasks.isNotEmpty())
        Assert.assertEquals("应该有2个任务", 2, tasks.size)

        val firstTask = tasks.first()//a
        viewModel.toggleTaskCompleted(firstTask.id, true)
        // Wait again
        advanceUntilIdle()
        val progress = viewModel.progress.first()
        Assert.assertEquals(0.5f, progress, 0.01f)  // Add "delta" to avoid floating-point precision issues
    }

}

