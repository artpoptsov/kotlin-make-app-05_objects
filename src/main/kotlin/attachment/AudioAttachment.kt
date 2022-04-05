package attachment

data class AudioAttachment(
    val audio: Audio,
    override val type: String = "audio"
): Attachment
