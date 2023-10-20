package com.example.docsolutions.model

data class PostGetUsers(
    val body: GetUsersBody
)

data class GetUsersBody(
    val SearchText: String
)
