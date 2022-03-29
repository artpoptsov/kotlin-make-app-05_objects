package attachment

data class DocAttachment(
    val doc: Doc,
    override val type: String
): Attachment
