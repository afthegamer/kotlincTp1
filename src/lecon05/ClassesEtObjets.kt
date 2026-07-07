package lecon05

// =============================================================================
// LEÇON 5 — CLASSES ET OBJETS
// =============================================================================
// Ce que tu vas apprendre :
//   - ce qu'est une classe (un moule) et un objet (ce qui sort du moule)
//   - créer un objet SANS écrire "new" (ce mot-clé n'existe pas en Kotlin)
//   - le constructeur primaire : class Personne(val nom: String, var age: Int)
//   - accéder aux propriétés avec le point : p.nom, p.age
//   - les méthodes (des fun à l'intérieur de la classe) et le mot-clé this
//   - override fun toString() pour contrôler l'affichage d'un objet
//   - private set : tout le monde peut LIRE, seule la classe peut ÉCRIRE
//   - companion object : la partie partagée par TOUTES les instances
//   - le constructeur secondaire "constructor(...)" : utile quand on veut
//     séparer la déclaration des propriétés de leur initialisation
// =============================================================================

// En Kotlin, une classe se déclare en dehors de main(), au niveau du fichier.

// ----- Personne : le constructeur primaire -----
// Une classe est un MOULE : elle décrit ce que contient chaque objet (ses
// propriétés) et ce qu'il sait faire (ses méthodes). Un objet est ce qui
// SORT du moule : on peut en fabriquer autant qu'on veut, tous indépendants.
// La parenthèse juste après le nom de la classe est le CONSTRUCTEUR PRIMAIRE.
// En mettant val ou var devant un paramètre, Kotlin crée directement la
// propriété correspondante : val = lecture seule, var = modifiable.
class Personne(val nom: String, var age: Int) {

    // Une méthode est simplement une fun écrite DANS la classe.
    // Elle a accès aux propriétés de l'objet sur lequel on l'appelle.
    fun sePresenter() {
        println("Je m'appelle $nom et j'ai $age ans.")
    }

    // "this" désigne l'objet courant (celui sur lequel la méthode est appelée).
    // Ici il est facultatif (this.age et age désignent la même chose), mais il
    // devient obligatoire quand un paramètre porte le même nom qu'une propriété.
    fun vieillir() {
        this.age = this.age + 1
    }

    // Sans rien faire, println(p) affiche un truc du genre "lecon05.Personne@4f3f".
    // En redéfinissant toString() avec le mot-clé override, on choisit le texte.
    override fun toString(): String {
        return "Personne(nom=$nom, age=$age)"
    }
}

// ----- Joueur : private set -----
class Joueur(val pseudo: String) {

    // "private set" : la propriété se LIT depuis l'extérieur (joueur.score)
    // mais ne peut être MODIFIÉE que par le code de la classe elle-même.
    // Tout le monde est donc obligé de passer par notre méthode de contrôle.
    var score: Int = 0
        private set

    // La méthode publique qui sert de porte d'entrée pour modifier le score.
    fun marquer(points: Int) {
        if (points > 0) {
            score = score + points
        } else {
            println("Points refusés : $points (il faut un nombre positif).")
        }
    }
}

// ----- Produit : companion object -----
class Produit(val nom: String) {

    // ++compteur incrémente le compteur PARTAGÉ puis donne sa nouvelle valeur :
    // chaque produit reçoit ainsi un identifiant unique (1, 2, 3, ...).
    val id: Int = ++compteur

    // Le companion object est la partie de la classe PARTAGÉE par toutes les
    // instances : il existe en un seul exemplaire, quel que soit le nombre
    // d'objets créés. Parfait pour un compteur d'identifiants.
    companion object {
        var compteur: Int = 0
    }
}

// ----- Rectangle : mention du constructeur SECONDAIRE -----
// Kotlin permet aussi d'écrire le constructeur dans le corps de la classe,
// avec le mot-clé "constructor". Dans cette forme, les
// propriétés sont déclarées à part, et le constructeur les remplit.
class Rectangle {
    var largeur: Int = 0
    var hauteur: Int = 0

    constructor(largeur: Int, hauteur: Int) {
        // Ici "this" est OBLIGATOIRE : sans lui, "largeur" serait le paramètre.
        this.largeur = largeur
        this.hauteur = hauteur
    }
}

fun main() {

    // ===== 1. UNE CLASSE = UN MOULE, UN OBJET = CE QUI EN SORT =====
    // Pour créer un objet, on appelle la classe comme une fonction.
    // PAS de "new" en Kotlin : on écrit Personne(...), pas new Personne(...).
    val alice = Personne("Alice", 30)
    val bruno = Personne("Bruno", 25)
    println("Deux objets sortis du même moule : ${alice.nom} et ${bruno.nom}")

    // ===== 2. ACCÉDER AUX PROPRIÉTÉS AVEC LE POINT =====
    println("Nom du premier objet : ${alice.nom}")
    println("Âge du premier objet : ${alice.age}")
    alice.age = 31              // age est un var : on peut le modifier
    println("Âge après modification : ${alice.age}")
    // alice.nom = "Alex"       // ERREUR si on décommente : nom est un val

    // ===== 3. LES MÉTHODES ET this =====
    alice.sePresenter()
    bruno.sePresenter()         // chaque objet utilise SES propres valeurs
    bruno.vieillir()            // dans vieillir(), this désigne bruno
    println("Bruno après vieillir() : ${bruno.age} ans")

    // ===== 4. override fun toString() =====
    println("Affichage grâce à toString : $alice")
    println(bruno)              // println appelle toString() tout seul

    // ===== 5. private set : LECTURE PUBLIQUE, ÉCRITURE INTERNE =====
    val joueuse = Joueur("Nadia")
    joueuse.marquer(50)
    joueuse.marquer(-10)        // refusé par la méthode
    println("Score de ${joueuse.pseudo} : ${joueuse.score}")
    // joueuse.score = 9999     // ERREUR si on décommente : le setter est privé

    // ===== 6. companion object : LE COMPTEUR PARTAGÉ =====
    val p1 = Produit("Clavier")
    val p2 = Produit("Souris")
    println("${p1.nom} a l'id ${p1.id}, ${p2.nom} a l'id ${p2.id}")
    println("Nombre total de produits créés : ${Produit.compteur}")

    // ===== 7. LE CONSTRUCTEUR SECONDAIRE =====
    val r = Rectangle(3, 4)
    println("Rectangle de ${r.largeur} x ${r.hauteur}")

    // ===== À TOI DE JOUER =====
    // Décommente les appels un par un, au fur et à mesure que tu écris
    // le code demandé dans chaque fonction exercice ci-dessous.
    // exercice1()
    // exercice2()
    // exercice3()
}

fun exercice1() {
    // Énoncé : déclare (au niveau du fichier, en dehors de cette fonction)
    // une classe Livre(titre, auteur) avec un constructeur primaire, puis
    // redéfinis toString() pour qu'un livre s'affiche "titre de auteur".
    // Ici, crée deux livres et affiche-les avec println.
    // Indice : override fun toString(): String { return "$titre de $auteur" }
}

fun exercice2() {
    // Énoncé : ajoute à ta classe Livre un companion object avec un compteur,
    // et une propriété "val numero: Int = ++compteur" pour que chaque livre
    // créé reçoive un numéro unique. Crée trois livres et affiche leurs numéros.
    // Indice : regarde la classe Produit plus haut, c'est le même mécanisme.
}

fun exercice3() {
    // Énoncé : crée une classe CompteBancaire avec une propriété solde en
    // "private set", une méthode deposer(montant) et une méthode retirer(montant).
    // Les deux refusent les opérations invalides (montant négatif ou nul, retrait
    // supérieur au solde) en affichant un message au lieu de toucher au solde.
    // Teste : dépose 100, retire 30, essaie de retirer 500, affiche le solde final.
    // Indice : var solde: Double = 0.0 puis, sur la ligne du dessous, private set.
}
