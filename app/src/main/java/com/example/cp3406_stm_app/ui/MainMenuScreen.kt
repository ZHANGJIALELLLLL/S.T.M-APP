package com.example.cp3406_stm_app.ui
//import com.example.cp3406_stm_app.MyApplication
//import com.example.cp3406_stm_app.data.DataStoreManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cp3406_stm_app.navigation.Routes




//import com.example.cp3406_stm_app.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(navController: NavController) {
//    val app = LocalContext.current.applicationContext as MyApplication  Temporary storage
//    val viewModel = remember { MainViewModel(DataStoreManager(app)) }
//    val email by viewModel.email.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("STM Main Menu") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//logo
            Text(
                text = "S.T.M APP",
                //
                fontSize = 32.sp,
                // fontWeight = FontWeight.Bold,
            )

//
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Routes.TASK) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Add Task") }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.COURSE) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Add Timetable") }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.PROGRESS) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("View Progress") }
        }
    }
}