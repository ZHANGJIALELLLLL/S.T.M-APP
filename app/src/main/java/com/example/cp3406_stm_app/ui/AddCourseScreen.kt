package com.example.cp3406_stm_app.ui
//import com.example.cp3406_stm_app.viewmodel.TaskViewModel
//import androidx.compose.material.icons.filled.Delete // 新增：删除图标
import android.app.Application
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cp3406_stm_app.viewmodel.CourseViewModel
import java.util.Calendar

//import com.example.cp3406_stm_app.viewmodel.CourseViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(navController: NavController) {
    val app = LocalContext.current.applicationContext as Application
    val viewModel = remember { CourseViewModel(app) }

    var courseName by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf("Monday") }
    var startTime by remember { mutableStateOf("08:00") }
    var endTime by remember { mutableStateOf("10:00") }
    val courses by viewModel.courses.collectAsState()

    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val context = LocalContext.current

    fun showTimePicker(onTimeSelected: (String) -> Unit) {
        val cal = Calendar.getInstance()
        TimePickerDialog(
            context,
            { _, hour, minute ->
                val formatted = String.format("%02d:%02d", hour, minute)
                onTimeSelected(formatted)
            },
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Course") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = courseName,
                onValueChange = { courseName = it },
                label = { Text("Course Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // 星期选择 Dropdown
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedDay,
                    onValueChange = {},
                    label = { Text("Day of Week") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    days.forEach { day ->
                        DropdownMenuItem(
                            text = { Text(day) },
                            onClick = {
                                selectedDay = day
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(onClick = { showTimePicker { startTime = it } }) {
                    Text("Start: $startTime")
                }
                OutlinedButton(onClick = { showTimePicker { endTime = it } }) {
                    Text("End: $endTime")
                }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (courseName.isNotBlank()) {
                        viewModel.addCourse(courseName, selectedDay, startTime, endTime)
                        courseName = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Course")
            }

            Spacer(Modifier.height(24.dp))
            Text("My Courses:", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(courses) { course ->
                    CourseItem(
                        name = course.name,
                        day = course.dayOfWeek,
                        start = course.startTime,
                        end = course.endTime,
                        onDelete = { viewModel.deleteCourse(course) }
                    )
                }
            }
        }
    }
}
@Composable
fun CourseItem(name: String, day: String, start: String, end: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(name, style = MaterialTheme.typography.titleSmall)
                Text("$day  $start - $end", style = MaterialTheme.typography.bodySmall)
            }
            TextButton(onClick = onDelete) { Text("🗑️") }
        }
    }
}
//            OutlinedTextField(
//                value = taskName,
//                onValueChange = { taskName = it },
//                label = { Text("Task Name") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(Modifier.height(12.dp))
//            Button(
//                onClick = {
//                    if (taskName.isNotBlank()) {
////                       viewModel.addTask(taskName)
//                        taskName = ""
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Add Task")
//            }

//            Spacer(Modifier.height(16.dp))
//            Text("Progress: ${(progress * 100).toInt()}%", style = MaterialTheme.typography.titleMedium)
//            LinearProgressIndicator(
//                progress = progress,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(8.dp)
//            )//进度条

//            Spacer(Modifier.height(24.dp))
//            LazyColumn {
//                items(tasks) { task ->
//                    TaskItem(
//                        name = task.name,
//                        completed = task.completed,
//                        onToggle = { viewModel.toggleTaskCompleted(task.id, !task.completed) }
//                    )
//                }
//            }
//        }
//    }

    // 定期清理过期任务
//    LaunchedEffect(Unit) {
//        viewModel.cleanExpired()
//    }
//}

//@Composable
//fun TaskItem(name: String, completed: Boolean, onToggle: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = if (completed) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = name,
//                style = MaterialTheme.typography.bodyLarge,
//                color = if (completed) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface
//            )
//            Button(
//                onClick = onToggle
//            ) {
//                Text(if (completed) "✅ Done" else "Mark Done")
//            }
//        }
//    }

//}