open class Vehicule(val marque: String, val annee: Int, val couleur: String) {

    open fun afficherDetails() {
        println("Marque : $marque")
        println("Annee : $annee")
        println("Couleur : $couleur")
    }

    open fun klaxonner() {
        println("Bip bip !")
    }
}
