package com.example.docsolutions.model

data class PostLogin(
    val body: LoginBody
)

data class LoginBody(
    val Username: String,
    val Password: String
)

