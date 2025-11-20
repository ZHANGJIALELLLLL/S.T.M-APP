package com.example.cp3406_stm_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.cp3406_stm_app.navigation.AppNavGraph
import com.example.cp3406_stm_app.ui.theme.Cp3406_STM_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cp3406_STM_APPTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavGraph()
                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "lele",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Surface(color = Color.Cyan) {
//        Text(
//            text = "Hi, my name is $name!",
//            modifier = modifier.padding(24.dp)
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Cp3406_STM_APPTheme {
//        Greeting("lele")
//    }
//}