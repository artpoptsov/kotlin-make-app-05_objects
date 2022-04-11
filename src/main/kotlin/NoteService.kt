import note.*

object NoteService {
    private val notes: MutableList<Note> = mutableListOf()
    private var noteId = 1
    private var commentId = 1

    fun add(note: Note): Int {
        val noteNewId = note.copy(nId = noteId, date = System.currentTimeMillis())
        notes.add(noteNewId)
        noteId += 1
        return notes.last().nId
    }

    fun delete(noteId: Int): Boolean {
        checkNote(noteId)
        val sourceNote = findNoteById(noteId)
        val deleteNote = Note(
            nId = sourceNote!!.nId,
            ownerId = sourceNote.ownerId,
            text = sourceNote.text,
            privacy = sourceNote.privacy,
            commentPrivacy = sourceNote.commentPrivacy,
            privacyView = sourceNote.privacyView,
            privacyComment = sourceNote.privacyComment,
            date = sourceNote.date,
            delete = true
        )
        notes[notes.indexOf(sourceNote)] = deleteNote
        return true
    }

    fun edit(inputNote: Note): Boolean {
        checkNote(inputNote.nId)
        val sourceNote = findNoteById(inputNote.nId)
        val editNote = Note(
            nId = sourceNote!!.nId,
            ownerId = sourceNote.ownerId,
            text = inputNote.text,
            privacy = inputNote.privacy,
            commentPrivacy = inputNote.commentPrivacy,
            privacyView = inputNote.privacyView,
            privacyComment = inputNote.privacyComment,
            date = sourceNote.date,
            delete = sourceNote.delete
        )
        notes[notes.indexOf(sourceNote)] = editNote
        return true
    }

    private fun noteExist(noteIdToFind: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (noteIdToFind == note.nId) {
                return true
            }
        }
        throw NoteNotFoundException("Error 180: Note not found")
    }

    private fun commentExist(commentIdToFind: Int): Boolean {
        for ((indexNote, note) in notes.withIndex()) {
            for ((indexComment, comment) in notes[indexNote].comment.withIndex()) {
                if (commentIdToFind == comment.cId) {
                    return true
                }
            }
        }
        throw CommentNotFoundException("Comment is not found")
    }

    private fun findNoteById(noteIdToFind: Int): Note? {
        for ((index, note) in notes.withIndex()) {
            if (noteIdToFind == note.nId) {
                return note
            }
        }
        return null
    }

    private fun findCommentById(commentIdToFind: Int): Comment? {
        for ((indexNote, note) in notes.withIndex()) {
            for ((indexComment, comment) in notes[indexNote].comment.withIndex()) {
                if (commentIdToFind == comment.cId) {
                    return comment
                }
            }
        }
        return null
    }

    private fun noteDeleteCheck(indexNote: Int) {
        if (findNoteById(indexNote)!!.delete) throw NoteNotFoundException("Note is delete")
    }

    private fun commentDeleteCheck(indexComment: Int) {
        if (findCommentById(indexComment)!!.delete) throw CommentNotFoundException("Comment is delete")
    }

    private fun commentRestoreCheck(indexComment: Int) {
        if (!findCommentById(indexComment)!!.delete) throw CommentNotFoundException("Comment is note delete")
    }

    private fun checkNote(indexNote: Int) {
        noteExist(indexNote)
        noteDeleteCheck(indexNote)
    }

    private fun checkComment(indexComment: Int) {
        commentExist(indexComment)
        commentDeleteCheck(indexComment)
    }

    fun printToScreen(indexNote: Int) {
        checkNote(indexNote)
        println(findNoteById(indexNote))
    }

    fun getById(noteId: Int): Note? {
        checkNote(noteId)
        return findNoteById(noteId)
    }

    fun createComment(comment: Comment): Int {
        checkNote(comment.noteId)

        val commentNewId = comment.copy(cId = commentId, date = System.currentTimeMillis())
        val indexNote = notes.indexOf(findNoteById(comment.noteId))

        notes[indexNote].comment.add(commentNewId)
        commentId += 1

        return notes[indexNote].comment.last().cId
    }

    fun deleteComment(commentId: Int): Boolean {
        checkComment(commentId)

        val sourceComment = findCommentById(commentId)

        val deleteComment = Comment(
            cId = sourceComment!!.cId,
            ownerId = sourceComment.ownerId,
            noteId = sourceComment.noteId,
            date = sourceComment.date,
            message = sourceComment.message,
            guid = sourceComment.guid,
            delete = true
        )

        val noteIndex = notes.indexOf(findNoteById(sourceComment.noteId))
        val commentIndex = notes[noteIndex].comment.indexOf(sourceComment)

        notes[noteIndex].comment[commentIndex] = deleteComment

        return true
    }

    fun restoreComment(commentId: Int): Boolean { // не доделан
        commentExist(commentId)
        commentRestoreCheck(commentId)

        val sourceComment = findCommentById(commentId)

        val restoreComment = Comment(
            cId = sourceComment!!.cId,
            ownerId = sourceComment.ownerId,
            noteId = sourceComment.noteId,
            date = sourceComment.date,
            message = sourceComment.message,
            guid = sourceComment.guid,
            delete = false
        )

        val noteIndex = notes.indexOf(findNoteById(sourceComment.noteId))
        val commentIndex = notes[noteIndex].comment.indexOf(sourceComment)

        notes[noteIndex].comment[commentIndex] = restoreComment

        return true
    }

    fun editComment(commentId: Int, newMessage: String): Boolean {
        checkComment(commentId)

        val sourceComment = findCommentById(commentId)

        val editComment = Comment(
            cId = sourceComment!!.cId,
            ownerId = sourceComment.ownerId,
            noteId = sourceComment.noteId,
            date = sourceComment.date,
            message = newMessage,
            guid = sourceComment.guid,
            delete = sourceComment.delete
        )

        val noteIndex = notes.indexOf(findNoteById(sourceComment.noteId))
        val commentIndex = notes[noteIndex].comment.indexOf(sourceComment)

        notes[noteIndex].comment[commentIndex] = editComment

        return true
    }

    fun get(noteIds: String, userId: Int, offset: Int, count: Int, sort: Int): List<Note> {

        val getNotes: MutableList<Note> = mutableListOf()
        val noteIdsList = noteIds.split(", ")
        var countIt = 1

        for ((index, noteIdStr) in noteIdsList.withIndex()) {
            val noteIdInt = noteIdStr.toInt()

            checkNote(noteIdInt)

            val note = findNoteById(noteIdInt)

            if (countIt <= count) {
                if (note!!.ownerId == userId) {
                    getNotes.add(note)
                    countIt += 1
                }
            }
        }

        return when (sort) {
            0 -> getNotes.sortedByDescending { it.date }
            1 -> getNotes.sortedBy { it.date }
            else -> throw SortNotFoundException("Указанный вид сортировки не поддерживается")
        }
    }

    fun getComments(noteId: Int, ownerId: Int, sort: Int, offset: Int, count: Int): List<Comment> {

        val getComments: MutableList<Comment> = mutableListOf()
        val note = findNoteById(noteId)
        var countIt = 1

        for ((indexComment, comment) in note!!.comment.withIndex()) {

            if (countIt <= count) {
                if (comment!!.ownerId == ownerId) {
                    getComments.add(comment)
                    countIt += 1
                }
            }

        }
        return when (sort) {
            0 -> getComments.sortedByDescending { it.date }
            1 -> getComments.sortedBy { it.date }
            else -> throw SortNotFoundException("Указанный вид сортировки не поддерживается")
        }
    }
}

