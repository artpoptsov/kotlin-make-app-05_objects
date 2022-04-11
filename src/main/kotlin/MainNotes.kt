import note.Comment
import note.Note
import java.util.*

fun main() {

    val note1 = Note(ownerId = 2, text = "Тестовый текст заметки №1")
    val note2 = Note(ownerId = 23, text = "Тестовый текст заметки №2")
    val note3 = Note(ownerId = 17, text = "Тестовый текст заметки №3")
    val note4 = Note(ownerId = 2, text = "Тестовый текст заметки №4")
    val note5 = Note(ownerId = 23, text = "Тестовый текст заметки №5")
    val note6 = Note(ownerId = 17, text = "Тестовый текст заметки №6")
    val note7 = Note(ownerId = 2, text = "Тестовый текст заметки №7")
    val note8 = Note(ownerId = 23, text = "Тестовый текст заметки №8")
    val note9 = Note(ownerId = 17, text = "Тестовый текст заметки №9")

    val note1edit = Note(nId = 1, ownerId = 12, text = "Изменённый текст заметки")

    val comment1 = Comment(noteId = 2, message = "Комментарий №1 к заметке №2", ownerId = 2)
    val comment2 = Comment(noteId = 1, message = "Комментарий №1 к заметке №1", ownerId = 2)

//    val comment3 = Comment(noteId = 3, message = "Комментарий №1 к заметке №3")

    NoteService.add(note1)
    Thread.sleep(50)
    NoteService.add(note2)
    Thread.sleep(50)
    NoteService.add(note3)
    Thread.sleep(50)
    NoteService.add(note4)
    Thread.sleep(50)
    NoteService.add(note5)
    Thread.sleep(50)
    NoteService.add(note6)
    Thread.sleep(50)
    NoteService.add(note7)
    Thread.sleep(50)
    NoteService.add(note8)
    Thread.sleep(50)
    NoteService.add(note9)

    NoteService.delete(3)

    NoteService.edit(note1edit)

    println(NoteService.getById(2))

    println(NoteService.createComment(comment1))
    println(NoteService.createComment(comment2))
    Thread.sleep(50)
    println(NoteService.createComment(comment2))
    Thread.sleep(50)
    println(NoteService.createComment(comment2))
    Thread.sleep(50)
    println(NoteService.createComment(comment2))
    Thread.sleep(50)
//    println(NoteService.createComment(comment3))

    println(NoteService.deleteComment(2))
    println(NoteService.restoreComment(2))
    println(NoteService.editComment(1, "Изменил комментарий, значит всё ОК"))

    NoteService.get("1, 7, 2, 6, 4", 2, 0, 2, 1)

    NoteService.getComments(1, 2, 1, 0, 2)

}