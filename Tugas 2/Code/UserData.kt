package com.example.tugas02

data class User(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val birthDate: String
)

object UserStore {
    var user: User? = User(
        firstName = "Nick",
        lastName = "Wilsan",
        username = "nickwilsan",
        email = "nickwilsan@gmail.com",
        password = "12345",
        phone = "081234567890",
        address = "Jl. Veteran No. 8, Malang",
        birthDate = "01/01/2005"
    )
}
