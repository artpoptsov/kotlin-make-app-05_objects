package attachment

data class VideoAttachment(
    val video: Video,
    override val type: String
): Attachment
