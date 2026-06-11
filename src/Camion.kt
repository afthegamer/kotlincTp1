class Camion(marque: String, annee: Int, couleur: String, val capaciteChargement: Double)
    : Vehicule(marque, annee, couleur) {

    override fun afficherDetails() {
        super.afficherDetails()
        println("Capacite de chargement : $capaciteChargement tonnes")
    }

    override fun klaxonner() {
        println("Pouet pouet !")
    }
}
