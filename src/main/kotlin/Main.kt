//package ru.netology

import attachment.Audio
import attachment.AudioAttachment

fun main() {

    val post = Post(date = 12011987, text = "Тест", friendOnly = true, like = 32, ownerId = 212)
    val post1 = Post(date = 12021912, text = "Тест2", friendOnly = false, like = 10, ownerId = 74)
    val updatePost1 = Post(date = 10121912, text = "я тебя изменил", friendOnly = true, like = 120, id = 1, ownerId = 3)

    val audioAttach = AudioAttachment(Audio(
        32,
        98,
        12,
        "Сергей Шнкуров",
        2365,
        "ссылка url",
        658789
    ))

    WallService.add(post)
    WallService.add(post1)
    println(WallService.update(updatePost1))

    println(WallService.attachmentAdd(1, audioAttach))

}


