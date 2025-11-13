package com.example.cp3406_stm_app.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cp3406_stm_app.ui.AddCourseScreen
import com.example.cp3406_stm_app.ui.AddTaskScreen
import com.example.cp3406_stm_app.ui.LoginScreen
import com.example.cp3406_stm_app.ui.MainMenuScreen
import com.example.cp3406_stm_app.ui.ProgressScreen

//import com.example.cp3406_stm_app.ui.LoginScreen
//import com.example.cp3406_stm_app.ui.SurveyScreen
//import com.example.cp3406_stm_app.ui.MainMenuScreen
//import com.example.cp3406_stm_app.ui.AddTaskScreen
//import com.example.cp3406_stm_app.ui.AddCourseScreen
//import com.example.cp3406_stm_app.ui.ProgressScreen
object Routes {
    const val LOGIN = "login"
    const val SURVEY = "survey"
    const val MAIN = "main"
    const val TASK = "task"
    const val COURSE = "course"
    const val PROGRESS = "progress"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginScreen(navController) }
//        composable(Routes.SURVEY) { SurveyScreen(navController) }
        composable(Routes.MAIN) { MainMenuScreen(navController) }
        composable(Routes.TASK) { AddTaskScreen(navController) }
        composable(Routes.COURSE) { AddCourseScreen(navController) }
        composable(Routes.PROGRESS) { ProgressScreen(navController) }
    }
}

