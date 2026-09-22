package com.example.tugas02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    onSaved: () -> Unit,
    onGoToLogin: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // supaya bisa di-scroll, field-nya banyak
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        Text(
            text = "Registrasi User",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Silakan lengkapi data diri kamu",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InputField(
                value = firstName,
                onValueChange = { firstName = it },
                label = "First Name",
                icon = Icons.Filled.Person,
                modifier = Modifier.weight(1f)
            )
            InputField(
                value = lastName,
                onValueChange = { lastName = it },
                label = "Last Name",
                icon = Icons.Filled.Person,
                modifier = Modifier.weight(1f)
            )
        }

        InputField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            icon = Icons.Filled.AccountCircle
        )
        InputField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            icon = Icons.Filled.Email,
            keyboardType = KeyboardType.Email
        )
        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            icon = Icons.Filled.Lock,
            isPassword = true
        )

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Identitas Tambahan",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(4.dp))


        InputField(
            value = phone,
            onValueChange = { phone = it },
            label = "No. Telepon",
            icon = Icons.Filled.Phone,
            keyboardType = KeyboardType.Phone
        )
        InputField(
            value = address,
            onValueChange = { address = it },
            label = "Alamat",
            icon = Icons.Filled.Home
        )
        InputField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = "Tanggal Lahir (cth: 01/01/2005)",
            icon = Icons.Filled.DateRange
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || username.isBlank() ||
                    email.isBlank() || password.isBlank()
                ) {
                    errorMessage = "First Name, Last Name, Username, Email, dan Password wajib diisi."
                } else {
                    errorMessage = ""
                    UserStore.user = User(
                        firstName = firstName,
                        lastName = lastName,
                        username = username,
                        email = email,
                        password = password,
                        phone = phone,
                        address = address,
                        birthDate = birthDate
                    )
                    onSaved()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        TextButton(onClick = onGoToLogin) {
            Text("Sudah punya akun? Login")
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        singleLine = true,
        visualTransformation =
            if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType
        ),
        modifier = modifier.padding(vertical = 4.dp)
    )
}
