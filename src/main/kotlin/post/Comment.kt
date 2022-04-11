package post

data class Comment(
    val owner_id: Int = 0,
    val post_id: Int,
    val from_group: Int = 0,
    val message: String = "",
    val sticker_id: Int = 0,
)
