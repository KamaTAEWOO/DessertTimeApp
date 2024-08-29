package com.desserttime.auth.model

data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val token: String
)
