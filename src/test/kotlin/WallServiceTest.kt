import attachment.Audio
import attachment.AudioAttachment
import org.junit.Test

import org.junit.Assert.*
import post.Comment
import post.Post
import post.PostNotFoundException

class WallServiceTest {

    @Test
    fun `result of add will be not zero`() {

        val service = WallService
        val result = service.add(Post(date = 12011987, text = "Тест", friendOnly = true, like = 32, ownerId = 21))
        assertTrue(result.id != 0)
    }

    @Test
     fun `update post with true id`() {

        val service = WallService

        service.add(Post(date = 12011987, text = "Текст в первом посте", friendOnly = true, like = 32, ownerId = 21))
        service.add(Post(date = 20032021, text = "Текст во втором посте", friendOnly = false, like = 7, ownerId = 5))
        service.add(Post(date = 29041998, text = "Текст в третьем посте", friendOnly = true, like = 14, ownerId = 9))

        val update = Post(id = 2, date = 23011999, text = "Исправленный текст в посте", friendOnly = false, like = 32, ownerId = 87)

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun `update post with false id`() {

        val service = WallService

        service.add(Post(date = 12011987, text = "Текст в первом посте", friendOnly = true, like = 32, ownerId = 21))
        service.add(Post(date = 20032021, text = "Текст во втором посте", friendOnly = false, like = 7, ownerId = 5))
        service.add(Post(date = 29041998, text = "Текст в третьем посте", friendOnly = true, like = 14, ownerId = 9))

        val update = Post(id = 23, date = 23011999, text = "Исправленный текст в посте", friendOnly = false, like = 32, ownerId = 87)

        val result = service.update(update)

        assertFalse(result)
    }

    @Test
    fun `attachment add is false because postId is not valid`() {

        val service = WallService

        val audioAttach = AudioAttachment(
            Audio(
            32,
            98,
            12,
            "Сергей Шнуров",
            2365,
            "ссылка url",
            658789
        )
        )

        val postIndex = 10

        val result = service.attachmentAdd(postIndex, audioAttach)

        assertFalse(result)
    }

    @Test
    fun `attachment add is true because postId is not valid`() {

        val service = WallService

        val audioAttach = AudioAttachment(
            Audio(
                32,
                98,
                12,
                "Сергей Шнкуров",
                2365,
                "ссылка url",
                658789
            )
        )

        val postIndex = 1

        val result = service.attachmentAdd(postIndex, audioAttach)

        assertTrue(result)
    }

    @Test
    fun `add comment to valid post`() {

        val service = WallService

        service.add(Post(date = 12011987, text = "Текст в первом посте", friendOnly = true, like = 32, ownerId = 21))
        service.add(Post(date = 20032021, text = "Текст во втором посте", friendOnly = false, like = 7, ownerId = 5))
        service.add(Post(date = 29041998, text = "Текст в третьем посте", friendOnly = true, like = 14, ownerId = 9))

        val comment = Comment(
            owner_id = 399,
            post_id = 2,
            message = "Второй комментарий за день",
        )

        val result = service.createComment(comment)

        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun `add comment to not valid post`() {

        val service = WallService

        val comment = Comment(
            owner_id = 399,
            post_id = 20,
            message = "Второй комментарий за день",
        )

        service.createComment(comment)
    }

    @Test
    fun `find post by id used printToScreen is OK`() {
        val service = WallService

        service.printToScreen(1)

        val result = service.printToScreen(1)

        assertNotNull(result)
    }

    @Test
    fun `find post by id used printToScreen is null`() {
        val service = WallService

        val result = service.printToScreen(30)

        assertNotNull(result)
    }

    @Test
    fun `print all posts`() {
        val service = WallService

        val result = service.printToScreenAll()

    }

}