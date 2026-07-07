package lecon07

// =====================================================================
//  LEÇON 7 — INTERFACES ET GÉNÉRIQUES
// =====================================================================
//  Ce que tu vas apprendre :
//   - une interface = un contrat : des fonctions déclarées SANS corps
//   - "signer" le contrat avec ":" et le mot-clé override (obligatoire)
//   - pourquoi c'est puissant : échanger une implémentation contre une
//     autre sans rien changer au reste du programme
//   - les génériques <T> : un "type à trou" qu'on remplit à l'usage,
//     illustrés par une petite classe Boite<T>
//   - combiner les deux : IStockage<T> + StockageEnMemoire<T>...
//     c'est le principe des vrais projets : un service qui ne dépend
//     que d'un contrat, jamais d'une implémentation précise.
// =====================================================================

// ----- LES DÉCLARATIONS (interfaces et classes vivent hors de main) -----

// Une INTERFACE est un contrat : elle liste des fonctions SANS corps.
// Elle dit "voilà ce qu'il faut savoir faire", sans dire COMMENT le faire.
interface Salueur {
    fun saluer(nom: String): String   // pas d'accolades : pas de corps !
}

// Une classe "signe" le contrat en écrivant ":" après son nom. Elle
// s'engage alors à donner un corps à CHAQUE fonction du contrat, en
// marquant chacune du mot-clé override (obligatoire, sinon erreur).
class SalueurPoli : Salueur {
    override fun saluer(nom: String): String {
        return "Bonjour $nom, enchanté de faire votre connaissance."
    }
}

// Une autre implémentation du MÊME contrat : autre style, même signature.
class SalueurCool : Salueur {
    override fun saluer(nom: String): String {
        return "Yo $nom !"
    }
}

// Cette fonction accepte N'IMPORTE QUEL Salueur : elle ne connaît que le
// contrat. On peut donc changer d'implémentation sans la modifier. C'est
// LA grande force des interfaces : le reste du code ne bouge pas.
fun accueillir(salueur: Salueur) {
    println("Accueil : ${salueur.saluer("Sam")}")
}

// ----- LES GÉNÉRIQUES : UN TYPE À TROU -----
// Le <T> après le nom de la classe est un "trou" : T sera remplacé par
// un vrai type au moment de l'utilisation (Boite<String>, Boite<Int>...).
// On écrit la classe UNE seule fois, et elle marche pour tous les types.
class Boite<T> {
    var contenu: T? = null            // T? : la boîte peut être vide (leçon 4)

    fun ranger(objet: T) {            // ici, objet est forcément du type T
        contenu = objet
    }

    fun sortir(): T? {                // T? car la boîte est peut-être vide
        return contenu
    }
}

// ----- COMBINER LES DEUX : UN CONTRAT GÉNÉRIQUE -----
// Une interface peut elle aussi avoir un trou <T>. Celle-ci décrit un
// endroit où stocker des objets, peu importe leur type, peu importe COMMENT.
interface IStockage<T> {
    fun ajouter(o: T): Boolean
    fun tous(): List<T>
    fun parIndex(i: Int): T?          // T? : l'index demandé n'existe peut-être pas
}

// Une implémentation possible : tout garder dans une MutableList (leçon 6).
// Demain on pourrait écrire StockageEnFichier<T> ou StockageEnBase<T> : le
// reste du programme, qui ne connaît que IStockage, ne changerait PAS.
//
// >>> C'est EXACTEMENT ce patron qu'on retrouve dans les vrais projets :
// >>> une interface de stockage (souvent appelée DAO) définit le contrat,
// >>> et un service travaille uniquement avec ce contrat, sans savoir
// >>> comment les données sont rangées. Tu le retrouveras dans un projet
// >>> à venir : ce que tu apprends ici, tu le reconnaîtras.
class StockageEnMemoire<T> : IStockage<T> {
    val elements = mutableListOf<T>()

    override fun ajouter(o: T): Boolean {
        elements.add(o)
        return true
    }

    override fun tous(): List<T> {
        return elements
    }

    override fun parIndex(i: Int): T? {
        if (i >= 0 && i < elements.size) {
            return elements[i]
        }
        return null                   // index hors limites -> null, pas de crash
    }
}

fun main() {

    // ===== 1. L'INTERFACE EN ACTION =====
    // Regarde bien le type des variables : c'est Salueur, le CONTRAT.
    // La valeur, elle, est une implémentation concrète au choix.
    val poli: Salueur = SalueurPoli()
    val cool: Salueur = SalueurCool()
    println("Poli : ${poli.saluer("Alice")}")
    println("Cool : ${cool.saluer("Alice")}")

    // ===== 2. ÉCHANGER UNE IMPLÉMENTATION CONTRE UNE AUTRE =====
    // accueillir() ne connaît que le contrat : on lui passe ce qu'on veut,
    // et son code à elle n'a pas changé d'une ligne entre les deux appels.
    accueillir(poli)
    accueillir(cool)

    // ===== 3. LES GÉNÉRIQUES : LE MÊME MOULE POUR TOUS LES TYPES =====
    // Ici on "remplit le trou" : T devient String, puis T devient Int.
    val boiteTexte = Boite<String>()
    boiteTexte.ranger("un secret")
    println("Boîte à texte : ${boiteTexte.sortir()}")

    val boiteNombre = Boite<Int>()
    boiteNombre.ranger(42)
    println("Boîte à nombre : ${boiteNombre.sortir()}")
    // boiteNombre.ranger("oups")  // <- ERREUR : cette boîte n'accepte que des Int

    // ===== 4. IStockage<T> : LE CONTRAT GÉNÉRIQUE =====
    val stock: IStockage<String> = StockageEnMemoire<String>()
    stock.ajouter("Salle A")
    stock.ajouter("Salle B")
    println("Tout le stock : ${stock.tous()}")
    println("Index 0 : ${stock.parIndex(0)}")
    println("Index 99 : ${stock.parIndex(99) ?: "rien à cet index"}")

    // ===== À TOI DE JOUER =====
    // Décommente les appels ci-dessous UN PAR UN, écris ton code dans
    // les fonctions plus bas, puis relance le programme pour vérifier.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ----- EXERCICE 1 : le polymorphisme avec des formes -----
fun exercice1() {
    // Énoncé : déclare (hors des fonctions, en bas du fichier) une
    // interface Forme avec une seule fonction : fun aire(): Double.
    // 1) Crée une classe Rectangle(largeur, hauteur) et une classe
    //    Cercle(rayon) qui signent le contrat Forme. Aire du cercle :
    //    3.14159 * rayon * rayon.
    // 2) Range un Rectangle et un Cercle dans une même List<Forme>.
    // 3) Parcours la liste avec for et affiche l'aire de chaque forme :
    //    le même appel exécute un code différent selon l'objet. C'est
    //    ça, le polymorphisme !
    // Indice : class Rectangle(val largeur: Double, val hauteur: Double) : Forme { ... }
}

// ----- EXERCICE 2 : un seul moule, deux boîtes -----
fun exercice2() {
    // Énoncé : crée une Boite<String> et une Boite<Int>.
    // 1) Range "bonjour" dans la première et 2026 dans la seconde.
    // 2) Affiche le contenu des deux avec sortir().
    // 3) Crée une troisième Boite<String> où tu ne ranges RIEN et
    //    affiche sortir() ?: "boîte vide" — tu vois le lien avec T? ?
    // Indice : le même moule Boite<T> a servi trois fois, zéro copier-coller.
}

// ----- EXERCICE 3 : un stockage de Double -----
fun exercice3() {
    // Énoncé : utilise IStockage<T> pour stocker des Double.
    // 1) Crée un StockageEnMemoire<Double>, en déclarant la variable
    //    avec le type IStockage<Double> comme dans la section 4.
    // 2) Ajoute trois températures avec ajouter(), puis affiche tous().
    // 3) Affiche parIndex(1), puis parIndex(10) : l'index 10 n'existe
    //    pas, tu dois obtenir null (sécurise l'affichage avec ?:).
    // Indice : val mesures: IStockage<Double> = StockageEnMemoire<Double>()
}
