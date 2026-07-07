package lecon10

// =====================================================================
//  LEÇON 10 — MAP, SET ET ENUM
// =====================================================================
//  (à faire après la leçon 6 (collections : List et MutableList))
//
//  Ce que tu vas apprendre :
//   - Set : une collection SANS doublons (setOf / mutableSetOf), add,
//     remove, le test "in", et l'astuce pour dédoublonner une liste
//   - Map : des paires clé -> valeur (mapOf / mutableMapOf), la syntaxe
//     "cle" to valeur, l'accès map[cle] qui est NULLABLE, .keys/.values,
//     l'itération, et getOrDefault / ?: pour gérer une clé absente
//   - enum class : un type à valeurs fixes et nommées, la liste avec
//     entries, et le when EXHAUSTIF (pas besoin de else)
//
//  Cette leçon est la SUITE directe de la leçon 6 : là tu avais les
//  listes (List / MutableList) ; ici on ajoute deux nouvelles collections
//  (Set et Map) et un type bien pratique (enum). Lance main() et lis les
//  commentaires dans l'ordre : chaque section affiche ses résultats.
// =====================================================================

fun main() {

    // ===== 1. SET : UNE COLLECTION SANS DOUBLONS =====
    // Rappel leçon 6 : une List accepte les doublons et garde l'ordre.
    // Un Set, lui, refuse les doublons : chaque valeur n'y figure qu'une
    // seule fois. On le crée avec setOf(...).
    val couleurs = setOf("rouge", "vert", "bleu", "rouge")
    // On a écrit "rouge" DEUX fois, mais le Set n'en garde qu'un seul.
    println("Set couleurs : $couleurs")     // [rouge, vert, bleu]
    println("Taille du set : ${couleurs.size}")  // 3 (et non 4 !)

    // Comme pour les listes, il existe une version modifiable :
    // mutableSetOf(...), qui a add() et remove().
    val panier = mutableSetOf("pomme", "banane")
    // add() renvoie un Boolean : true si la valeur a bien été ajoutée,
    // false si elle était DÉJÀ présente (dans ce cas rien n'est stocké).
    val ajout1 = panier.add("cerise")   // nouvelle -> true
    val ajout2 = panier.add("pomme")    // déjà là  -> false, ignorée
    println("Panier : $panier")                 // [pomme, banane, cerise]
    println("add(\"cerise\") a renvoyé : $ajout1")  // true
    println("add(\"pomme\")  a renvoyé : $ajout2")  // false

    // Le test "in" (vu en leçon 6 sur les listes) marche aussi sur un Set.
    println("banane est dans le panier ? ${"banane" in panier}")  // true
    // remove() retire la valeur donnée.
    panier.remove("banane")
    println("Après remove(\"banane\") : $panier")  // [pomme, cerise]

    // USAGE TYPIQUE : dédoublonner une liste. On appelle .toSet() sur une
    // liste et hop, les doublons disparaissent.
    val avecDoublons = listOf("Karim", "Alice", "Karim", "Bob", "Alice", "Karim")
    val uniques = avecDoublons.toSet()
    println("Liste de départ : $avecDoublons")  // [Karim, Alice, Karim, Bob, Alice, Karim]
    println("Sans doublons   : $uniques")       // [Karim, Alice, Bob]
    println("On passe de ${avecDoublons.size} à ${uniques.size} éléments")  // de 6 à 3

    // ===== 2. MAP : DES PAIRES CLÉ -> VALEUR =====
    // Une Map associe une CLÉ à une VALEUR (comme un dictionnaire : le mot
    // est la clé, la définition est la valeur). Les clés sont uniques.
    // On la crée avec mapOf(...) et la syntaxe "cle" to valeur.
    val capitales = mapOf(
        "France" to "Paris",
        "Japon" to "Tokyo",
        "Italie" to "Rome"
    )
    println("Map capitales : $capitales")  // {France=Paris, Japon=Tokyo, Italie=Rome}

    // Accès à une valeur par sa clé, avec les crochets [ ].
    val capFrance = capitales["France"]
    println("Capitale de la France : $capFrance")  // Paris

    // ATTENTION (rappel leçon 4 : null safety) : la clé demandée peut être
    // ABSENTE. Dans ce cas map[cle] vaut null. Le type de capitales["..."]
    // est donc String? (nullable), jamais String tout court.
    val capEspagne = capitales["Espagne"]  // "Espagne" n'est pas une clé
    println("Capitale de l'Espagne : $capEspagne")  // null

    // Deux façons propres de gérer l'absence :
    // a) l'opérateur ?: (leçon 4) donne une valeur de secours si c'est null
    val secours = capitales["Espagne"] ?: "inconnue"
    println("Avec ?: -> $secours")  // inconnue
    // b) getOrDefault(cle, valeurParDefaut) fait la même chose en une méthode
    val parDefaut = capitales.getOrDefault("Espagne", "inconnue")
    println("Avec getOrDefault -> $parDefaut")  // inconnue

    // Version modifiable : mutableMapOf(...). On ajoute/modifie avec
    // map[cle] = valeur, ou avec put(cle, valeur).
    val ages = mutableMapOf("Alice" to 30, "Bob" to 25)
    ages["Chloé"] = 28     // clé nouvelle -> on AJOUTE la paire
    ages["Alice"] = 31     // clé existante -> on MODIFIE la valeur
    ages.put("David", 40)  // put fait exactement comme la ligne au-dessus
    println("Ages : $ages")  // {Alice=31, Bob=25, Chloé=28, David=40}

    // .keys donne l'ensemble des clés, .values la collection des valeurs.
    println("Les clés    : ${ages.keys}")    // [Alice, Bob, Chloé, David]
    println("Les valeurs : ${ages.values}")  // [31, 25, 28, 40]

    // Itérer sur une Map : on décompose chaque paire en (cle, valeur).
    for ((nom, age) in ages) {
        println("$nom a $age ans")
    }
    // Affiche, dans l'ordre :
    //   Alice a 31 ans
    //   Bob a 25 ans
    //   Chloé a 28 ans
    //   David a 40 ans

    // ===== 3. ENUM CLASS : UN TYPE À VALEURS FIXES =====
    // Un enum sert à représenter un ensemble FERMÉ de valeurs nommées :
    // les jours de la semaine, les points cardinaux, les états d'une
    // commande... La liste des valeurs possibles est connue d'avance.
    // (La déclaration "enum class Jour { ... }" est plus bas dans le fichier.)

    // entries donne toutes les valeurs de l'enum, dans l'ordre de la
    // déclaration. (Avant Kotlin 1.9 on écrivait values() ; entries est
    // la version moderne recommandée.)
    println("Tous les jours : ${Jour.entries}")
    // Affiche : [LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE]

    // Chaque valeur a deux propriétés utiles :
    //   .name    -> son nom sous forme de texte
    //   .ordinal -> sa position dans la déclaration (à partir de 0)
    val jour = Jour.MERCREDI
    println("Nom : ${jour.name}")               // MERCREDI
    println("Position (ordinal) : ${jour.ordinal}")  // 2

    // LE GROS INTÉRÊT : le when devient EXHAUSTIF sur un enum. Comme Kotlin
    // connaît toutes les valeurs possibles, si tu les couvres toutes, il
    // n'exige PAS de branche else. Et si un jour tu ajoutes une valeur à
    // l'enum, le compilateur te forcera à traiter le nouveau cas. Pratique.
    for (j in Jour.entries) {
        val type = if (estWeekend(j)) "week-end" else "travail"
        println("$j : $type")
    }
    // Affiche :
    //   LUNDI : travail
    //   MARDI : travail
    //   MERCREDI : travail
    //   JEUDI : travail
    //   VENDREDI : travail
    //   SAMEDI : week-end
    //   DIMANCHE : week-end

    // ===== À TOI DE JOUER =====
    // Décommente les appels ci-dessous UN PAR UN, écris ton code dans les
    // fonctions plus bas, puis relance le programme pour vérifier.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ----- L'enum utilisé dans les démos et l'exercice 3 -----
// (l'ordre n'a pas d'importance en Kotlin : on peut le déclarer sous main)
enum class Jour {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
}

// when EXHAUSTIF : tous les cas de Jour sont couverts, donc PAS de else.
fun estWeekend(jour: Jour): Boolean = when (jour) {
    Jour.SAMEDI, Jour.DIMANCHE -> true
    Jour.LUNDI, Jour.MARDI, Jour.MERCREDI, Jour.JEUDI, Jour.VENDREDI -> false
}

// ----- EXERCICE 1 : dédoublonner une liste avec un Set -----
fun exercice1() {
    // Énoncé : pars d'une liste qui contient des doublons, par exemple
    // listOf("chat", "chien", "chat", "oiseau", "chien").
    // 1) Transforme-la en Set pour ne garder que les valeurs uniques.
    // 2) Affiche la liste de départ, puis le Set obtenu.
    // 3) Affiche le nombre d'animaux différents (la taille du Set).
    // Indice : la méthode .toSet() fait tout le travail de dédoublonnage.
    // Indice : le nombre d'éléments, c'est .size (comme pour une List).
    // (à toi d'écrire ton code ici)
}

// ----- EXERCICE 2 : compter les occurrences de mots avec une Map -----
fun exercice2() {
    // Énoncé : pars d'une liste de mots, par exemple
    // listOf("pomme", "banane", "pomme", "cerise", "banane", "pomme").
    // Construis une MutableMap<String, Int> qui associe à chaque mot le
    // nombre de fois où il apparaît, puis affiche-la.
    // Résultat attendu : {pomme=3, banane=2, cerise=1}
    // 1) Crée une map vide avec mutableMapOf<String, Int>().
    // 2) Parcours la liste avec un for. Pour chaque mot, récupère son
    //    compteur actuel dans la map (0 s'il est absent) et remets-le +1.
    // 3) Affiche la map à la fin.
    // Indice : compteur actuel = map[mot] ?: 0  (rappel : map[mot] est
    //    nullable, l'opérateur ?: fournit 0 quand le mot est nouveau).
    // Indice : ensuite map[mot] = (map[mot] ?: 0) + 1
    // (à toi d'écrire ton code ici)
}

// ----- EXERCICE 3 : un when exhaustif sur un enum -----
fun exercice3() {
    // Énoncé : réutilise l'enum Jour déclaré plus haut. Écris une fonction
    // programme(jour: Jour): String qui renvoie, avec un when EXHAUSTIF
    // (donc SANS else), un message par jour, par exemple :
    //   LUNDI    -> "réunion d'équipe"
    //   VENDREDI -> "bilan de la semaine"
    //   SAMEDI et DIMANCHE -> "repos"
    //   les autres -> "journée de code"
    // Puis parcours Jour.entries et affiche "JOUR : message" pour chacun.
    // Indice : dans le when, tu peux regrouper plusieurs valeurs sur une
    //    même ligne : Jour.SAMEDI, Jour.DIMANCHE -> "repos".
    // Indice : comme tous les cas de Jour sont couverts, le compilateur
    //    n'exige PAS de branche else — c'est tout l'intérêt de l'enum.
    // (à toi d'écrire ton code ici)
}
