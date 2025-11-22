package com.example.cp3406_stm_app.ui

//import androidx.compose.ui.graphics.drawscope.arcTo
//import androidx.compose.ui.graphics.drawscope.drawArc
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cp3406_stm_app.viewmodel.TaskViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(navController: NavController) {
    val app = LocalContext.current.applicationContext as android.app.Application
    val viewModel = remember { TaskViewModel(app)}
    val tasks by viewModel.tasks.collectAsState()
    val progressDegree by viewModel.progressDegrees.collectAsState()//0-360
    //
    val progressValue by viewModel.progressValue.collectAsState()//0-100
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("View Progress") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back") // 返回
                    }
                }
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
            Text("Task Completion", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))

            // A disc-shaped progress bar
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    val sweepAngle: Float = progressDegree //new,范围调整0到360度
                    drawArc(
                        color = Color.LightGray,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = true
                    )
                    drawArc(
                        color = Color(0xFF4CAF50),
                        startAngle = -90f,
                        sweepAngle = sweepAngle,
                        useCenter = true
                    )
                }
                Text(
                    text = "${progressValue}%",//0到100
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(Modifier.height(32.dp))

            // Display the total number of tasks and the number of completed tasks
            Text("Total tasks: ${tasks.size}", style = MaterialTheme.typography.bodyLarge)
            Text("Completed: ${tasks.count { it.completed }}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
