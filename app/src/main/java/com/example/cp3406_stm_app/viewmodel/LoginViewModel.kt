package com.example.cp3406_stm_app.viewmodel
//import com.example.cp3406_stm_app.data.DataStoreManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
//test is empty
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _remember = MutableStateFlow(false)
    val remember: StateFlow<Boolean> = _remember

    init {
        viewModelScope.launch {
//            _email.value = dataStore.emailFlow.first() ?: ""
//            _remember.value = dataStore.rememberFlow.first()
        }
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun toggleRemember() {
        _remember.value = !_remember.value
    }

    fun saveLoginState() {
        viewModelScope.launch {
//            dataStore.saveUser(_email.value, _remember.value)
        }
    }
}