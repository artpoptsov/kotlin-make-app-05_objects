package attachment

data class Graffiti(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val Photo604: String,
    val type: String = "graffiti"
)

