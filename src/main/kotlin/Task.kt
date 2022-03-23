package ru.netology

import java.util.*

fun main() {

    val post = Post(1, 2,1,12011987, "Тест", true, 32, 87)
    println(post)

}

data class Post(
    val id: Long,
    val ownerId: Long,
    val fromId: Long,
    val date: Int,
    val text: String,
    val friendOnly: Boolean,
    val like: Int,
    val views: Int
)

object WallService {
    private var posts = emptyArray<Post>()

}

