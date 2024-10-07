package com.example.lab09

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lab09.api.UserApiService
import com.example.lab09.model.UserModel

@Composable
fun ScreenUsers(navController: NavHostController, servicio: UserApiService) {
    var users by remember { mutableStateOf(listOf<UserModel>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        users = servicio.getUsers()
        loading = false
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            users.forEach { user ->
                UserRow(user) {
                    navController.navigate("userDetail/${user.id}")
                }
            }
        }
    }
}

@Composable
fun UserRow(user: UserModel, onClick: () -> Unit) {
    Column(modifier = Modifier
        .padding(16.dp)
        .clickable(onClick = onClick)) {
        Text(text = user.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = user.username, style = MaterialTheme.typography.bodyMedium)
        Text(text = user.email, style = MaterialTheme.typography.bodySmall)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun ScreenUserDetail(navController: NavHostController, servicio: UserApiService, userId: Int) {
    // Implementa la lógica para mostrar los detalles del usuario
}
