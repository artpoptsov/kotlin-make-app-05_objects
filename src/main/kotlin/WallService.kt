import attachment.Attachment

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var newId = 1

    fun add(post: Post): Post {
        val postNewId = post.copy(id = newId)
        posts += postNewId
        newId += 1
        return posts.last()
    }

    fun update(postUp: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (postUp.id == post.id) {
                val sourcePost = posts[postUp.id - 1]
                val postUpdate = Post(
                id = postUp.id,
                date = sourcePost.date,
                ownerId = sourcePost.ownerId,
                text = postUp.text,
                friendOnly = postUp.friendOnly,
                like = postUp.like
            )
                posts.set(post.id - 1, postUpdate)
                return true
            }
        }
        return false
    }

    fun printToScreen(index: Int) {
        println(WallService.findPostById(index))
    }

    fun printToScreenAll() {
        for ((index, post) in posts.withIndex()) {
            println(posts[index])
        }
    }

    fun attachmentAdd(postId: Int, attach: Attachment): Boolean {
        val postIndex = postId - 1

        if (posts.size <= postIndex) {
            return false
        } else {
            posts[postIndex].attachments += attach
            return true
        }
    }

    fun createComment(comment: Comment): Boolean {
        val postIndex = comment.post_id - 1

        if (posts.size <= postIndex) {
            throw PostNotFoundException("Пост ID = ${comment.post_id} не найден")
        } else {
            posts[postIndex].comments += comment
            return true
        }
    }

    private fun findPostById(postIdToFind: Int): Post? {
        for ((index, post) in posts.withIndex()) {
            if (postIdToFind == post.id) {
                return post
            }
        }
        return null
    }
}