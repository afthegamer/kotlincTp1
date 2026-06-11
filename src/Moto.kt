class Moto(marque: String, annee: Int, couleur: String, val aUnSidecar: Boolean)
    : Vehicule(marque, annee, couleur) {

    override fun afficherDetails() {
        super.afficherDetails()
        if (aUnSidecar) {
            println("Sidecar : oui")
        } else {
            println("Sidecar : non")
        }
    }

    override fun klaxonner() {
        println("Biip biip !")
    }
}
