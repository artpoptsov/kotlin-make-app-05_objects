package attachment

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val userId: Int,
    val date: Int,
    val type: String = "photo"
)

