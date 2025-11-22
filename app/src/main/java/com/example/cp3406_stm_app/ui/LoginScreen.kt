package com.example.cp3406_stm_app.ui
//import com.example.cp3406_stm_app.MyApplication
//import com.example.cp3406_stm_app.data.DataStoreManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cp3406_stm_app.navigation.Routes
import com.example.cp3406_stm_app.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
//    val context = LocalContext.current.applicationContext as MyApplication
//    val dataStore = remember { DataStoreManager(context) }
    val viewModel = remember { LoginViewModel() }

    val email by viewModel.email.collectAsState()
    val remember by viewModel.remember.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Checkbox(checked = remember, onCheckedChange = { viewModel.toggleRemember() })
            Text("Remember me")
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                scope.launch {
                    viewModel.saveLoginState()
//
                    navController.navigate(Routes.MAIN)//Jump to the survey page
                }
            },
            enabled = email.isNotBlank(),//If the email is empty, it cannot be clicked
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}