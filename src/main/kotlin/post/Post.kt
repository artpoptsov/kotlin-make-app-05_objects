package post

import attachment.Attachment
import post.Comment

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendOnly: Boolean,
    val copyright: Int? = 0,
    val like: Int?,
    val reposts: Int? = 0,
    val views: Int? = 0,
    val postType: String = "",
    val postSource: Int? = 0,
    val geo: String? = "",
    val signerId: Int = 0,
    val copyHistory: Int? = 0,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Int? = 0,
    val postponedId: Int = 0,
    var attachments: Array<Attachment> = emptyArray(),
    var comments: Array<Comment> = emptyArray(),
)