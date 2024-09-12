class Archive(val name: String) {
    var notes: Notes = Notes()
}
class Archives() {
    var archivesList: MutableList<Archive> = mutableListOf()
    fun add(archive: Archive) {
        archivesList.add(archive)
    }
}
