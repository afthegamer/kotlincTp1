class Voiture(marque: String, annee: Int, couleur: String, val nombreDePortes: Int)
    : Vehicule(marque, annee, couleur) {

    override fun afficherDetails() {
        super.afficherDetails()
        println("Nombre de portes : $nombreDePortes")
    }

    override fun klaxonner() {
        println("Tut tut !")
    }
}
