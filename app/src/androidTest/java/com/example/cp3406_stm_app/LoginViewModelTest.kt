package com.example.cp3406_stm_app

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406_stm_app.viewmodel.LoginViewModel
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
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var app: Application

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() = runTest {
        Dispatchers.setMain(testDispatcher)

        // Create ViewModel
        viewModel = LoginViewModel()//loginviewmodel is empty

        // Wait for the "init" block to complete
        advanceUntilIdle()
    }

    @After
    fun cleanup() = runTest {
        Dispatchers.resetMain()
    }

    @Test
    fun updateEmail_updatesEmailState() = runTest {
        val testEmail = "user@test.com"
        viewModel.updateEmail(testEmail)

        val email = viewModel.email.first()
        Assert.assertEquals(testEmail, email)
    }

    @Test
    fun toggleRemember_changesRememberState() = runTest {
        // The initial value should be "false"
        val initialState = viewModel.remember.first()
        Assert.assertFalse(initialState)

        // It should be "true" after the switch
        viewModel.toggleRemember()
        val toggledState = viewModel.remember.first()
        Assert.assertTrue(toggledState)

        // Switching again should return to "false"
        viewModel.toggleRemember()
        val finalState = viewModel.remember.first()
        Assert.assertFalse(finalState)
    }


}