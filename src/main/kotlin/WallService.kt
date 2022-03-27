package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var newId = 1

    fun add(post: Post): Post {
        val postNewId = post.copy(id = newId)
        posts += postNewId
        newId += 1
        return posts.last()
    }

    fun update(postUp: Post): Boolean {
        if (posts.size <= postUp.id - 1) {
            return false
        } else {

            val sourcePost = posts[postUp.id - 1]
            val postUpdate = Post(
                id = postUp.id,
                date = sourcePost.date,
                ownerId = sourcePost.ownerId,
                text = postUp.text,
                friendOnly = postUp.friendOnly,
                like = postUp.like
            )

            for ((index, post) in posts.withIndex()) {
                if (postUp.id == post.id) {
                    posts.set(post.id, postUpdate)
                    return true
                }
            }
            return false
        }
    }
}