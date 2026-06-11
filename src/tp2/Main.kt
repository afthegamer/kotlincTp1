package tp2

fun main() {
    val ss = SalleService()

    ss.create(Salle("A", "Salle 1"))
    ss.create(Salle("B", "Salle 2"))
    ss.create(Salle("C", "Salle 3"))
    ss.create(Salle("D", "Salle 4"))

    println("La liste des salles :")
    for (s in ss.findAll())
        println("\t$s")

    println("Supprimer la salle avec id = 1")
    ss.delete(ss.findById(1)!!)

    println("Modifier la salle avec id = 2")
    val salle = ss.findById(2)

    if (salle != null) {
        println("\tSalle à modifier : $salle")

        println("Donner le nouveau code :")
        val newCode = readlnOrNull() ?: ""
        salle.setCode(newCode)

        println("Donner le nouveau libelle :")
        val newLibelle = readlnOrNull() ?: ""
        salle.setLibelle(newLibelle)

        ss.update(salle)
    }

    println("La liste des salles après les mises à jour :")
    for (s in ss.findAll())
        println("\t$s")
}
