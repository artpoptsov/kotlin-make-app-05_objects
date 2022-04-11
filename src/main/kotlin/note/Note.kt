package note

data class Note(
    val nId: Int = 0,
    val date: Long = 0,
    val ownerId: Int = 0,
    val text: String = "",
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val privacyView: String = "",
    val privacyComment: String = "",
    val comment: MutableList<Comment> = mutableListOf(),
    val delete: Boolean = false
)
