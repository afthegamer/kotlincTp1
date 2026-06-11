class Garage {

    val vehicules = mutableListOf<Vehicule>()

    fun ajouterVehicule(vehicule: Vehicule) {
        vehicules.add(vehicule)
    }

    fun afficherGarage() {
        println("--- Vehicules du garage ---")
        for (vehicule in vehicules) {
            println()
            vehicule.afficherDetails()
        }
    }
}
