data class Note(
    val name: String,
    val text: String) {
}

class Notes {
    var notesList: MutableList<Note> = mutableListOf()

    fun add(note: Note) {
        notesList.add(note)
    }
}