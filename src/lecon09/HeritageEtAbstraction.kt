package lecon09

// =============================================================================
// LEÇON — HÉRITAGE ET CLASSES ABSTRAITES
// =============================================================================
// (à faire après la leçon 5 (classes et objets))
//
// Ce que tu vas apprendre :
//   - open class : en Kotlin une classe est FERMÉE par défaut ; le mot-clé
//     "open" autorise l'héritage. Une sous-classe hérite avec ": Base(args)"
//     et réutilise gratuitement les propriétés/méthodes de la base
//   - override + super : redéfinir une méthode marquée "open", et appeler
//     quand même la version parente avec "super.methode()"
//   - abstract class : une classe qu'on NE PEUT PAS instancier, qui peut
//     imposer des méthodes SANS corps que chaque sous-classe DOIT écrire
//   - la différence entre classe abstraite et interface (leçon 7)
//   - polymorphisme : une variable de type Base peut contenir n'importe
//     quelle sous-classe ; on range le tout dans une List<Base> (leçon 6)
//   - is / as : tester et convertir le type réel d'un objet, avec smart cast
//
// C'est EXACTEMENT le socle d'un projet "gestion de véhicules" : une classe
// Vehicule ouverte, et Voiture / Camion / Moto qui en héritent.
//
// Lance main() et lis les commentaires dans l'ordre : chaque section affiche
// son résultat pour que tu voies concrètement ce qui se passe.
// =============================================================================

// ----- LES DÉCLARATIONS (les classes vivent HORS de main, dans n'importe
//       quel ordre : le compilateur les trouve toutes seul) -----

// Une classe "open" ACCEPTE d'avoir des enfants. Sans ce mot-clé, Kotlin
// refuserait qu'on hérite d'Animal (par défaut, une classe est "final").
// Ici Animal a une propriété (nom) et deux méthodes.
open class Animal(val nom: String) {

    // "open" DEVANT une méthode = "les enfants ont le droit de me redéfinir".
    // Sans "open" ici, aucune sous-classe ne pourrait remplacer crier().
    open fun crier() {
        println("$nom fait un bruit.")
    }

    // Cette méthode n'est PAS open : les enfants l'héritent telle quelle et
    // ne peuvent pas la changer. C'est du code écrit UNE fois, réutilisé partout.
    fun dormir() {
        println("$nom dort. Zzz")
    }
}

// Chien hérite d'Animal avec ": Animal(nom)". Le "(nom)" passe la valeur au
// constructeur du parent : c'est lui qui range nom dans la propriété.
class Chien(nom: String) : Animal(nom) {

    // override = "je remplace la version du parent". Obligatoire, sinon erreur.
    override fun crier() {
        println("$nom aboie : Ouaf ouaf !")
    }

    // Une méthode PROPRE à Chien : elle n'existe pas chez Animal. Un enfant
    // peut réutiliser ce qu'il hérite ET ajouter ses propres capacités.
    fun rapporter() {
        println("$nom rapporte le bâton.")
    }
}

// Chat : autre enfant d'Animal, autre cri. Même moule de base, comportement
// différent. Note qu'il n'a PAS de méthode rapporter() : elle est à Chien.
class Chat(nom: String) : Animal(nom) {
    override fun crier() {
        println("$nom miaule : Miaou.")
    }
}

// ChienDeGarde sert à montrer "super" : au lieu de REMPLACER complètement le
// comportement du parent, il le GARDE puis ajoute le sien par-dessus.
class ChienDeGarde(nom: String) : Animal(nom) {
    override fun crier() {
        super.crier()                          // d'abord le bruit du parent Animal
        println("$nom grogne en plus : Grrr !") // puis SON comportement à lui
    }
}

// ----- UNE CLASSE ABSTRAITE : LE MOULE INACHEVÉ -----
// "abstract" = cette classe ne s'instancie PAS directement (Forme() est
// interdit). Elle sert uniquement de parent commun. Elle peut mélanger :
//   - des méthodes ABSTRAITES (sans corps) que chaque enfant DOIT écrire
//   - des méthodes CONCRÈTES (avec corps) partagées par tous les enfants
//
// Différence avec une interface (leçon 7) : une classe abstraite peut avoir
// un ÉTAT (des propriétés qui stockent des valeurs) et un constructeur ;
// une interface, elle, ne peut PAS conserver d'état.
abstract class Forme {

    // Méthode abstraite : pas d'accolades, pas de corps. Impossible de dire
    // "l'aire d'une forme" en général -> on FORCE chaque enfant à la définir.
    abstract fun aire(): Double

    // Méthode concrète : elle, on sait l'écrire pour tout le monde, car elle
    // s'appuie sur aire() que les enfants fourniront. Zéro duplication.
    fun decrire() {
        println("Cette forme a une aire de ${aire()}.")
    }
}

// Carre DOIT donner un corps à aire() (sinon le code ne compile pas), car il
// hérite d'une méthode abstraite. Note le "()" après Forme : on appelle le
// constructeur du parent, même s'il est vide.
class Carre(val cote: Double) : Forme() {
    override fun aire(): Double {
        return cote * cote
    }
}

// Rectangle : autre enfant, autre formule d'aire. decrire() marche pour lui
// sans qu'on ait rien à réécrire : il l'a héritée de Forme.
class Rectangle(val largeur: Double, val hauteur: Double) : Forme() {
    override fun aire(): Double {
        return largeur * hauteur
    }
}

fun main() {

    // ===== 1. OPEN CLASS : HÉRITER, C'EST RÉUTILISER =====
    // Médor est un Chien, mais il sait TOUT ce que sait un Animal, gratuitement.
    val medor = Chien("Médor")
    medor.dormir()        // méthode HÉRITÉE d'Animal, pas réécrite dans Chien
    medor.rapporter()     // méthode PROPRE à Chien (Animal ne l'a pas)
    println("Nom hérité : ${medor.nom}")   // propriété héritée du parent
    // -> Médor dort. Zzz
    // -> Médor rapporte le bâton.
    // -> Nom hérité : Médor

    // ===== 2. OVERRIDE ET super =====
    // Chaque enfant a REDÉFINI crier() : même appel, résultat différent.
    medor.crier()                       // -> Médor aboie : Ouaf ouaf !
    val felix = Chat("Félix")
    felix.crier()                       // -> Félix miaule : Miaou.
    // ChienDeGarde, lui, réutilise le cri du parent AVANT d'ajouter le sien
    // grâce à super.crier() : d'où DEUX lignes affichées.
    val rex = ChienDeGarde("Rex")
    rex.crier()
    // -> Rex fait un bruit.
    // -> Rex grogne en plus : Grrr !

    // ===== 3. CLASSE ABSTRAITE ET MEMBRES abstract =====
    // val f = Forme()   // ERREUR si tu décommentes : une classe abstraite
    //                   // ne s'instancie pas. On instancie ses ENFANTS.
    val carre = Carre(5.0)
    val rect = Rectangle(4.0, 3.0)
    println("Aire du carré : ${carre.aire()}")       // -> Aire du carré : 25.0
    println("Aire du rectangle : ${rect.aire()}")    // -> Aire du rectangle : 12.0
    carre.decrire()   // méthode CONCRÈTE héritée -> Cette forme a une aire de 25.0.
    rect.decrire()    // la même, mais aire() renvoie autre chose -> ...12.0.

    // ===== 4. POLYMORPHISME : UNE List<Animal> QUI CONTIENT DES ENFANTS =====
    // Le type déclaré est Animal, mais chaque case contient un objet RÉEL
    // différent (Chien, Chat, Animal). Au moment de l'appel, Kotlin choisit
    // la vraie version de crier() selon l'objet, pas selon le type déclaré.
    val animaux: List<Animal> = listOf(
        Chien("Médor"),
        Chat("Félix"),
        Animal("Perroquet")
    )
    for (a in animaux) {
        a.crier()     // même ligne de code, trois comportements différents
    }
    // -> Médor aboie : Ouaf ouaf !
    // -> Félix miaule : Miaou.
    // -> Perroquet fait un bruit.

    // ===== 5. is / as : CONNAÎTRE ET CONVERTIR LE TYPE RÉEL =====
    // "is Type" teste si l'objet est réellement de ce type. Bonus : après un
    // test réussi, Kotlin fait un SMART CAST (rappel leçon 4) -> dans la
    // branche, a est déjà vu comme un Chien, sans conversion à écrire.
    for (a in animaux) {
        when (a) {
            is Chien -> a.rapporter()   // a est un Chien ici : rapporter() est permis
            is Chat  -> println("${a.nom} est un chat, il ronronne.")
            else     -> println("${a.nom} est un animal quelconque.")
        }
    }
    // -> Médor rapporte le bâton.
    // -> Félix est un chat, il ronronne.
    // -> Perroquet est un animal quelconque.

    // "as" force une conversion quand TU es sûr du type réel. Ici animaux[0]
    // est bien un Chien, donc le cast réussit (attention : un as vers le
    // mauvais type ferait planter le programme).
    val premier: Animal = animaux[0]
    val chien = premier as Chien
    chien.rapporter()   // -> Médor rapporte le bâton.

    // ===== À TOI DE JOUER =====
    // Décommente les appels un par un, au fur et à mesure que tu écris le
    // code demandé dans chaque fonction exercice ci-dessous.
    // exercice1()
    // exercice2()
    // exercice3()
}

fun exercice1() {
    // Énoncé : déclare (au niveau du fichier, en dehors de cette fonction)
    // une classe ouverte Vehicule(val marque: String) avec une méthode
    // afficherDetails() qui affiche la marque, et une méthode open klaxonner()
    // qui affiche un klaxon générique. Crée ensuite une classe
    // Voiture(marque, val nbPortes: Int) : Vehicule(marque) qui REDÉFINIT
    // klaxonner() ("Tuut tuut !"). Ici, crée une Voiture, appelle
    // afficherDetails() (héritée) puis klaxonner() (redéfinie).
    // Indice : open class Vehicule(...) { ... open fun klaxonner() { ... } }
    // Indice : class Voiture(marque: String, val nbPortes: Int) : Vehicule(marque)
    // (à toi d'écrire ton code ici)
}

fun exercice2() {
    // Énoncé : transforme l'idée en classe ABSTRAITE. Déclare
    // abstract class Vehicule2(val marque: String) avec une méthode
    // abstract fun klaxonner() (sans corps). Crée deux enfants, Voiture2 et
    // Moto2, qui override klaxonner() chacun à sa façon. Range-les dans une
    // List<Vehicule2>, parcours-la avec for et appelle klaxonner() sur chacun :
    // c'est le polymorphisme, comme dans la section 4.
    // Indice : abstract fun klaxonner()  -> chaque enfant DOIT le override.
    // Indice : val parc: List<Vehicule2> = listOf(Voiture2("Ford"), Moto2("Honda"))
    // (à toi d'écrire ton code ici)
}

fun exercice3() {
    // Énoncé : reprends ta liste de Vehicule2 de l'exercice 2. Parcours-la et,
    // avec when (v) { is ... }, affiche un message SPÉCIFIQUE selon le type
    // réel : par exemple "C'est une moto" pour une Moto2, sinon "Autre véhicule".
    // Profite du smart cast pour appeler, dans la branche is Moto2, une méthode
    // ou une propriété qui n'existe QUE sur Moto2 (ex : un booléen sidecar).
    // Indice : when (v) { is Moto2 -> ... ; else -> ... }
    // Indice : dans la branche is Moto2, v est déjà vu comme un Moto2.
    // (à toi d'écrire ton code ici)
}
