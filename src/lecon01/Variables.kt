package lecon01

// ============================================================================
//  LEÇON 1 — LES VARIABLES
// ============================================================================
//  Ce que tu vas apprendre :
//   - afficher du texte dans la console avec println
//   - déclarer des variables avec val (non réassignable) et var (réassignable)
//   - laisser Kotlin deviner le type tout seul (l'inférence de type)
//   - écrire le type explicitement quand tu le souhaites (val x: Int)
//   - les types de base : Int, Double, String, Boolean
//   - les opérations arithmétiques simples
//   - insérer des valeurs dans un texte avec les templates de chaînes
//
//  Lance ce fichier (la fonction main) et lis les commentaires en parallèle
//  de ce qui s'affiche dans la console : c'est comme ça qu'on apprend.
// ============================================================================

fun main() {

    // ===== 1. PRINTLN ET LA CONSOLE =====
    // println affiche ce qu'on lui donne dans la console, puis passe à la ligne.
    // C'est l'outil de base pour observer ce que fait ton programme.
    println("===== 1. println et la console =====")
    println("Bonjour ! Je suis ton premier programme Kotlin.")
    // Remarque : pas de point-virgule en fin de ligne. En Kotlin il est
    // optionnel, et en pratique on ne l'écrit jamais.

    // ===== 2. VAL (NON RÉASSIGNABLE) VS VAR (RÉASSIGNABLE) =====
    println("===== 2. val vs var =====")
    // val déclare une variable qu'on ne peut PAS réassigner : une fois sa
    // valeur donnée, elle est définitive (comme final en Java ou const en JS).
    val ville = "Paris"
    println("ville = " + ville)
    // Si tu décommentes la ligne suivante, le programme ne compile plus :
    // ville = "Lyon"   // ERREUR : "Val cannot be reassigned"

    // var déclare une variable qu'on PEUT réassigner autant de fois qu'on veut.
    var humeur = "curieuse"
    println("humeur au départ : " + humeur)
    humeur = "enthousiaste" // réassignation : pas de "var" ici, juste le nom
    println("humeur ensuite : " + humeur)
    // Pourquoi préférer val ? Une valeur qui ne change jamais est plus facile
    // à suivre : tu sais qu'elle vaut la même chose partout dans le code, donc
    // moins de surprises et moins de bugs. Le réflexe Kotlin : val d'abord,
    // et var seulement si tu as vraiment besoin de modifier la valeur.

    // ===== 3. L'INFÉRENCE DE TYPE (KOTLIN DEVINE) =====
    println("===== 3. l'inférence de type =====")
    // Tu n'as pas écrit de type ci-dessus, et pourtant chaque variable en a un.
    // Kotlin DÉDUIT le type à partir de la valeur : c'est l'inférence de type.
    val annee = 2026       // Kotlin devine : Int (un entier)
    val rayon = 3.5        // Kotlin devine : Double (un nombre à virgule)
    val langage = "Kotlin" // Kotlin devine : String (du texte)
    val sympa = true       // Kotlin devine : Boolean (vrai ou faux)
    println("annee = " + annee)
    println("rayon = " + rayon)
    println("langage = " + langage)
    println("sympa = " + sympa)
    // Le type existe bel et bien : une fois déduit, il ne change plus.
    // annee = "deux mille vingt-six"  // ERREUR : annee est un Int, pas un String

    // ===== 4. ÉCRIRE LE TYPE EXPLICITEMENT =====
    println("===== 4. le type explicite =====")
    // Tu peux aussi écrire le type toi-même, après un deux-points : "nom: Type".
    // C'est utile pour clarifier ton intention, ou pour forcer un type précis.
    val nombreDeRoues: Int = 4
    val temperature: Double = 21.0 // ici on force Double ; sans annotation, val t = 21 donnerait un Int (et 21.0 un Double)
    println("nombreDeRoues = " + nombreDeRoues)
    println("temperature = " + temperature)

    // ===== 5. LES TYPES DE BASE : Int, Double, String, Boolean =====
    println("===== 5. les types de base =====")
    val age: Int = 30          // Int : un nombre entier (positif ou négatif)
    val taille: Double = 1.75  // Double : un nombre à virgule (le point en code !)
    val prenom: String = "Sam" // String : du texte, toujours entre guillemets
    val majeur: Boolean = true // Boolean : seulement deux valeurs, true ou false
    println("age = " + age)
    println("taille = " + taille)
    println("prenom = " + prenom)
    println("majeur = " + majeur)

    // ===== 6. LES OPÉRATIONS ARITHMÉTIQUES SIMPLES =====
    println("===== 6. les opérations arithmétiques =====")
    val a = 7
    val b = 2
    println("a + b = " + (a + b)) // addition       : 9
    println("a - b = " + (a - b)) // soustraction   : 5
    println("a * b = " + (a * b)) // multiplication : 14
    // Attention au piège : entre deux Int, la division est ENTIÈRE.
    println("a / b = " + (a / b)) // 3, et pas 3.5 !
    println("a % b = " + (a % b)) // modulo (le reste de la division) : 1
    // Dès qu'un Double entre en jeu, le résultat devient un Double.
    println("7.0 / 2 = " + (7.0 / 2)) // 3.5, cette fois

    // ===== 7. LES TEMPLATES DE CHAÎNES =====
    println("===== 7. les templates de chaînes =====")
    // Coller des textes avec + devient vite pénible. Les templates permettent
    // d'insérer une variable directement dans la chaîne avec $nomDeVariable.
    val heros = "Alex"
    val nbCafes = 3
    println("$heros a bu $nbCafes cafés ce matin.")
    // Pour une EXPRESSION (un calcul, par exemple), on l'entoure de ${ ... }.
    println("Demain, $heros en boira ${nbCafes + 1}.")
    // C'est la façon idiomatique d'afficher des valeurs en Kotlin :
    // à partir de maintenant, utilise les templates plutôt que le +.

    // ===== À TOI DE JOUER =====
    // Les trois exercices sont définis sous main(), chacun avec son énoncé
    // en commentaire. Écris ton code dans leur corps, puis décommente les
    // appels ci-dessous UN PAR UN, au fur et à mesure que tu avances.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ----------------------------------------------------------------------------
// EXERCICE 1 — La présentation
// ----------------------------------------------------------------------------
fun exercice1() {
    // Énoncé : crée trois variables — nom (String), age (Int), taille (Double) —
    // puis affiche-les dans UNE SEULE phrase grâce à un template de chaîne.
    // Exemple de sortie : Je m'appelle Nina, j'ai 25 ans et je mesure 1.68 m.
    // Indice : tout tient dans un seul println("...") avec des $ à l'intérieur.
    // (à toi d'écrire ton code ici)
}

// ----------------------------------------------------------------------------
// EXERCICE 2 — Le bureau de change
// ----------------------------------------------------------------------------
fun exercice2() {
    // Énoncé : convertis un montant d'euros en dollars.
    // 1. Déclare une val tauxDeChange qui vaut 1.08 (1 euro = 1.08 dollar).
    // 2. Déclare une val montantEnEuros (par exemple 50.0).
    // 3. Calcule le montant en dollars dans une troisième val (multiplication).
    // 4. Affiche le tout avec un template, par exemple : 50.0 euros = 54.0 dollars
    // Indice : le taux ne change pas pendant le programme... val ou var ?
    // (à toi d'écrire ton code ici)
}

// ----------------------------------------------------------------------------
// EXERCICE 3 — Le compteur
// ----------------------------------------------------------------------------
fun exercice3() {
    // Énoncé : déclare un compteur entier qui démarre à 0, incrémente-le 3 fois,
    // et affiche sa valeur APRÈS CHAQUE incrémentation avec un template.
    // Sortie attendue : Compteur : 1   puis   Compteur : 2   puis   Compteur : 3
    // Indice : il va être réassigné, donc val ou var ? Pour ajouter 1 :
    // compteur = compteur + 1 (il existe aussi la version courte compteur += 1).
}
