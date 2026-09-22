package com.example.tugas02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showForgotDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AvatarCircle(
            size = 130.dp,
            background = MaterialTheme.colorScheme.tertiary
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Masuk ke akun kamu",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(28.dp))

        InputField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            icon = Icons.Filled.AccountCircle
        )

        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            icon = Icons.Filled.Lock,
            isPassword = true
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val saved = UserStore.user
                when {
                    saved == null ->
                        errorMessage = "Belum ada akun. Silakan registrasi dulu."
                    username == saved.username && password == saved.password -> {
                        errorMessage = ""
                        onLoginSuccess()
                    }
                    else ->
                        errorMessage = "Username atau password salah."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        TextButton(onClick = { showForgotDialog = true }) {
            Text("Lupa Password?")
        }

        TextButton(onClick = onGoToRegister) {
            Text("Belum punya akun? Registrasi")
        }
    }

    if (showForgotDialog) {
        AlertDialog(
            onDismissRequest = { showForgotDialog = false },
            title = { Text("Lupa Password") },
            text = {
                Text("Silakan hubungi admin di admin@tugas02.com untuk mereset password kamu.")
            },
            confirmButton = {
                TextButton(onClick = { showForgotDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
