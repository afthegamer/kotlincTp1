package tp2

class SalleService : IDao<Salle> {
    private val salles = mutableListOf<Salle>()

    override fun create(o: Salle): Boolean {
        return salles.add(o)
    }

    override fun update(o: Salle): Boolean {
        for (s in salles) {
            if (s.id == o.id) {
                s.setCode(o.code)
                s.setLibelle(o.libelle)
                return true
            }
        }
        return false
    }

    override fun delete(o: Salle): Boolean {
        return salles.remove(o)
    }

    override fun findAll(): List<Salle> {
        return salles
    }

    override fun findById(id: Int): Salle? {
        return salles.find { it.id == id }
    }
}
