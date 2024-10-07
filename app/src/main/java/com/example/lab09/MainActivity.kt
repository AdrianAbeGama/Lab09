package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab09.api.UserApiService
import com.example.lab09.ui.theme.Lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var servicio: UserApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa Retrofit y UserApiService
        servicio = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)

        setContent {
            Lab09Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    Contenido(navController, servicio)
                }
            }
        }
    }
}

@Composable
fun Contenido(navController: NavHostController, servicio: UserApiService) {
    NavHost(navController, startDestination = "users") {
        composable("users") { ScreenUsers(navController, servicio) }
        composable("userDetail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: -1
            ScreenUserDetail(navController, servicio, userId)
        }
    }
}
