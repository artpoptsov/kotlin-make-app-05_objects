import attachment.Audio
import attachment.AudioAttachment

fun main() {

    val post = Post(date = 12011987, text = "Пост №1", friendOnly = true, like = 32, ownerId = 212)
    val post1 = Post(date = 12021912, text = "Пост №2", friendOnly = false, like = 10, ownerId = 74)
    val post2 = Post(date = 13221912, text = "Пост №3", friendOnly = false, like = 1, ownerId = 2)

    val updatePost1 = Post(date = 10121912, text = "Пост обновил", friendOnly = true, like = 120, id = 2, ownerId = 3)

    val audioAttach = AudioAttachment(Audio(
        32,
        98,
        12,
        "Сергей Шнкуров",
        2365,
        "ссылка url",
        658789
    ))

    val comment1 = Comment(
        owner_id = 20,
        post_id = 2,
        message = "Самый лучший комментарий",
    )
    val comment2 = Comment(
        owner_id = 399,
        post_id = 2,
        message = "Второй комментарий за день",
    )

    WallService.add(post)
    WallService.add(post1)
    WallService.add(post2)

    println(WallService.update(updatePost1))

    println(WallService.attachmentAdd(3, audioAttach))

    WallService.createComment(comment1)
    WallService.createComment(comment2)

//    WallService.printToScreenAll()

}


