package note

data class Comment(
    val cId: Int = 0,
    val ownerId: Int = 0,
    val noteId: Int,
    val date: Long = 0,
    val message: String = "",
    val guid: Int = 0,
    val delete: Boolean = false
)
