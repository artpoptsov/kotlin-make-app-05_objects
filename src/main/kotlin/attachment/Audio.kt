package attachment

data class Audio(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val artist: String,
    val duration: Int,
    val url: String,
    val date: Int,
    val type: String = "audio"
)
