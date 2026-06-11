package tp2

class Salle {
    var id: Int
        private set
    var code: String
        private set
    var libelle: String
        private set

    companion object {
        private var comp = 0
    }

    constructor(code: String, libelle: String) {
        this.id = ++comp
        this.code = code
        this.libelle = libelle
    }

    constructor(id: Int, code: String, libelle: String) {
        this.id = id
        this.code = code
        this.libelle = libelle
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun setLibelle(libelle: String) {
        this.libelle = libelle
    }

    override fun toString(): String {
        return "$code $libelle"
    }
}
