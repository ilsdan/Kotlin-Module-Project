import java.util.Scanner

class ArchivesView(var archives: Archives) {

     fun show() {
        println("Архивы")
        println("N - Новый архив")
        println("E - Выход из программы")
        do {

            if (archives.archivesList.isEmpty()) {
                println("Архивы отсутствуют!")
            } else {
                println("Список архивов:")
                archives.archivesList.forEachIndexed {
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

            if (archives.archivesList.getOrNull(command.toInt()) == null) {
                println("Архив отсутствует!")
                continue
            } else {
                val notesView: NotesView = NotesView(archives.archivesList.get(command.toInt()).notes)
                notesView.show()
                continue
            }

        } while (true)

    }

    private fun new() {
        println("Новый архив:")
        do {
            val name = Scanner(System.`in`).nextLine()

            if (name.isEmpty()) {
                println("Имя не может быть пустым")
                continue
            }

            archives.add(Archive(name))
            break

        } while (true)
    }
}