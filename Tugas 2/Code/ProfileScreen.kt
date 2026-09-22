package com.example.tugas02

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    onOpenAvatar: () -> Unit
) {
    val user = UserStore.user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(8.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onOpenAvatar() }
        ) {
            AvatarCircle(
                size = 120.dp,
                background = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "Ketuk untuk ubah avatar",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = if (user != null) "${user.firstName} ${user.lastName}" else "-",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = if (user != null) "@${user.username}" else "-",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow("First Name", user?.firstName)
                InfoRow("Last Name", user?.lastName)
                InfoRow("Username", user?.username)
                InfoRow("Email", user?.email)
                InfoRow("Password", user?.password?.let { "•".repeat(it.length) })
                InfoRow("No. Telepon", user?.phone)
                InfoRow("Alamat", user?.address)
                InfoRow("Tanggal Lahir", user?.birthDate, showDivider = false)
            }
        }

        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = onOpenAvatar,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Lihat Halaman Avatar")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(Icons.Filled.ExitToApp, contentDescription = null)
            Spacer(Modifier.size(8.dp))
            Text("Logout", fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun InfoRow(label: String, value: String?, showDivider: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = if (value.isNullOrBlank()) "-" else value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1.4f)
        )
    }
    if (showDivider) HorizontalDivider()
}
