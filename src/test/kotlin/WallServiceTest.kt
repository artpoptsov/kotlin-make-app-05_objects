import org.junit.Test

import org.junit.Assert.*
import ru.netology.Post
import ru.netology.WallService

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

}