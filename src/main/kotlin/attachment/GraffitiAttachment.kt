package attachment

data class GraffitiAttachment(
    val graffiti: Graffiti,
    override val type: String
): Attachment
