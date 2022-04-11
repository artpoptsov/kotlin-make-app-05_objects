import note.CommentNotFoundException
import note.Note
import note.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*


class NoteServiceTest {

    @Test
    fun `add note to notes is true`() {
        val service = NoteService

        val result = service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        assertTrue(result != 0)
    }

    @Test
    fun `delete note is true`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        val result = service.delete(1)

        assertTrue(result)
    }

    @Test
    fun `edit note is true`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        val result = service.edit(Note(nId = 2, ownerId = 12, text = "Изменённый текст заметки"))

        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun `note is not exist`() {

        val service = NoteService

        service.delete(3)
    }

    @Test(expected = NoteNotFoundException::class)
    fun `note is delete`() {
        val service = NoteService

        service.delete(3)
        service.delete(3)
    }

    @Test
    fun `print to screen is done`() {
        val service = NoteService

        service.printToScreen(1)
    }

    @Test
    fun `get note by id`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        val result= service.getById(1)

        assertNotNull(result)
    }

    @Test
    fun `create commnet`() {
        val service = NoteService

        val result = service.createComment(note.Comment(noteId = 4, message = "Комментарий №1 к заметке №1", ownerId = 2))

        assertTrue(result != 0)
    }

    @Test
    fun `delete comment`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        service.createComment(note.Comment(noteId = 4, message = "Комментарий №1 к заметке №1", ownerId = 2))
        val result = service.deleteComment(1)

        assertTrue(result)
    }

    @Test
    fun `restore comment is true`() {
        val service = NoteService

        val result = service.restoreComment(1)

        assertTrue(result)
    }

    @Test
    fun `edit comment`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        service.createComment(note.Comment(noteId = 1, message = "Комментарий №1 к заметке №1", ownerId = 2))
        val result = service.editComment(2, "Новая запись")

        assertTrue(result)
    }

    @Test
    fun `get notes`() {
        val service = NoteService
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))
        service.add(Note(ownerId = 2, text = "Тестовый текст заметки №1"))

        val result = service.get("1, 4, 2", 1, 0,2,0)

        assertNotNull(result)
    }

}