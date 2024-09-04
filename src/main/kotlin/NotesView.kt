import java.util.Scanner

class NotesView(val notes: Notes)  {
    fun show() {
        println("Заметки")
        println("N - Новая заметка")
        println("E - Выход в меню архивов")
        do {

            if (notes.notesList.isEmpty()) {
                println("Заметки отсутствуют!")
            } else {
                println("Список заметок:")
                notes.notesList.forEachIndexed {
                        index, archive ->
                    println("$index ${archive.name}")
                }
            }

            val command = Scanner(System.`in`).nextLine()

            if (command == "N") {
                new()
                continue
            }

            if (command == "E") {
                break
            }

            if (command.toIntOrNull() == null) {
                println("Ошибка ввода")
                continue
            }

            if (notes.notesList.getOrNull(command.toInt()) == null) {
                println("Заметка отсутствует!")
                continue
            } else {
                val note = notes.notesList.get(command.toInt())
                println("Заметка ${note.name}:")
                println(note.text)
                continue
            }

        } while (true)

    }

    private fun new() {
        println("Новая заметка:")
        do {
            println("Имя заметка:")
            val name = Scanner(System.`in`).nextLine()

            if (name.isEmpty()) {
                println("Имя не может быть пустым")
                continue
            }
            println("Текст заметки:")
            val text = Scanner(System.`in`).nextLine()

            notes.add(Note(name, text))
            break

        } while (true)
    }
}