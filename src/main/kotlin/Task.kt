package ru.netology

fun main() {

    val post = Post(date = 12011987, text = "Тест", friendOnly = true, like = 32, ownerId = 21)
    val post1 = Post(date = 12021912, text = "Тест2", friendOnly = false, like = 10, ownerId = 74)
    val updatePost1 = Post(date = 10121912, text = "я тебя изменил", friendOnly = true, like = 120, id = 1, ownerId = 3)

    WallService
    WallService.add(post)
    WallService.add(post1)
    println(WallService.update(updatePost1))

}

data class Post(
    var id: Int = 0,
    val date: Int,
    val ownerId: Int,
    val text: String,
    val friendOnly: Boolean,
    val like: Int
)

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1

    fun add(post: Post): Post {
        post.id = this.id
        posts += post
        id += 1
        return posts.last()

    }

    fun update(post: Post): Boolean {
        if (posts.size <= post.id - 1) {
            return false
        } else {

            val sourcePost = posts[post.id - 1]
            val postUpdate = Post(post.id, sourcePost.date, sourcePost.ownerId, post.text, post.friendOnly, post.like)

            for ((index, post) in posts.withIndex()) {
                if (index == post.id - 1) {
                    posts.set(post.id, postUpdate)
                    return true
                }
            }
            return false
        }
    }

}

