import java.util.Scanner

class View(var archives: Archives) {

    var currentArchive: Int? = null

    fun showArchives() {

        do {

            if (currentArchive == null) {
                println("Архивы:")
                println("0. - Новый архив")
                printArchives()
                println("${archives.archivesList.count()+1}. - Выход из программы")
            } else {
                println("Заметки:")
                println("0. - Новая заметка")
                printNotes()
                println("${archives.archivesList.get(currentArchive!!).notes.notesList.count()+1}. - Назад к архивам")
            }

            val command = Scanner(System.`in`).nextLine()

            if (command.toIntOrNull() == null) {
                println("Ошибка ввода")
                continue
            }

            if (command.toInt() == 0) {
                newItem()
                continue
            }

            var exitCommand: Int
            if (currentArchive == null) {
                exitCommand = archives.archivesList.count()+1
            } else {
                exitCommand = archives.archivesList.get(currentArchive!!).notes.notesList.count() + 1
            }

            if (command.toInt() == exitCommand) {
                if (currentArchive == null) {
                    break
                } else {
                    currentArchive = null
                    continue
                }
            }

            val selectedItem = command.toInt()-1

            if (currentArchive == null) {
                if (archives.archivesList.getOrNull(selectedItem) == null) { /////////
                    println("Архив отсутствует!")
                    continue
                } else {
                    currentArchive = selectedItem
                    continue
                }
            } else {
                if (archives.archivesList.get(currentArchive!!).notes.notesList.getOrNull(selectedItem) == null) {
                    println("Заметка отсутствует!")
                    continue
                } else {
                    val note = archives.archivesList.get(currentArchive!!).notes.notesList.get(selectedItem)
                    println("Заметка ${note.name}:")
                    println(note.text)
                    continue
                }
            }

        } while (true)
    }

    private fun printArchives() {
        if (archives.archivesList.isEmpty()) {
            println("Архивы отсутствуют!")
        } else {
            //println("Список архивов:")
            archives.archivesList.forEachIndexed {
                    index, archive ->
                println("${index+1}. ${archive.name}")
            }
        }
    }

    private fun printNotes() {
        if (archives.archivesList.get(currentArchive!!).notes.notesList.isEmpty()) {
            println("Заметки отсутствуют!")
        } else {
            //println("Список заметок:")
            archives.archivesList.get(currentArchive!!).notes.notesList.forEachIndexed {
                    index, archive ->
                println("${index+1}. ${archive.name}")
            }
        }
    }

    private fun newItem() {
        val name = getNoEmptyStringOrRepeat("Введите имя:")
        if (currentArchive == null) {
            archives.add(Archive(name))
        } else {
            val text = getNoEmptyStringOrRepeat("Введите текст заметки:")
            archives.archivesList.get(currentArchive!!).notes.notesList.add(Note(name, text))
        }
    }

    private fun getNoEmptyStringOrRepeat(title:String): String {
        println(title)
        do {
            val string = Scanner(System.`in`).nextLine()
            if (string.isEmpty()) {
                println("Значение не может быть пустым")
                continue
            }
            return string
        } while (true)
    }
}