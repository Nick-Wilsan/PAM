package com.example.tugas02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tugas02.ui.theme.Tugas02Theme

enum class Screen { REGISTER, LOGIN, PROFILE, AVATAR }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas02Theme {
                AppRouter()
            }
        }
    }
}

@Composable
fun AppRouter() {
    var screen by remember { mutableStateOf(Screen.LOGIN) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (screen) {
                Screen.REGISTER -> RegisterScreen(
                    onSaved = { screen = Screen.LOGIN },
                    onGoToLogin = { screen = Screen.LOGIN }
                )

                Screen.LOGIN -> LoginScreen(
                    onLoginSuccess = { screen = Screen.PROFILE },
                    onGoToRegister = { screen = Screen.REGISTER }
                )

                Screen.PROFILE -> ProfileScreen(
                    onLogout = { screen = Screen.LOGIN },
                    onOpenAvatar = { screen = Screen.AVATAR }
                )

                Screen.AVATAR -> AvatarScreen(
                    onBack = { screen = Screen.PROFILE }
                )
            }
        }
    }
}
