fun main() {
    val garage = Garage()

    val voiture = Voiture("Renault", 2021, "Bleue", 5)
    val camion = Camion("Volvo", 2019, "Blanc", 12.5)
    val moto = Moto("Yamaha", 2022, "Noire", false)

    garage.ajouterVehicule(voiture)
    garage.ajouterVehicule(camion)
    garage.ajouterVehicule(moto)

    garage.afficherGarage()

    println()
    println("--- Klaxons ---")
    for (vehicule in garage.vehicules) {
        vehicule.klaxonner()
    }

    println()
    println("--- Types de vehicules ---")
    for (vehicule in garage.vehicules) {
        if (vehicule is Voiture) {
            println(vehicule.marque + " est une voiture.")
        } else if (vehicule is Camion) {
            println(vehicule.marque + " est un camion.")
        } else if (vehicule is Moto) {
            println(vehicule.marque + " est une moto.")
        }
    }
}
