package attachment

data class PhotoAttachment(
    val photo: Photo,
    override val type: String
): Attachment
