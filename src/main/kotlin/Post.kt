package ru.netology

data class Post(
    val id: Int = 0,
    val date: Int,
    val ownerId: Int,
    val text: String,
    val friendOnly: Boolean,
    val like: Int
)