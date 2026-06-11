package lecon06

// =====================================================================
//  LEÇON 6 — LES COLLECTIONS (les listes)
// =====================================================================
//  Ce que tu vas apprendre :
//   - List (lecture seule) vs MutableList (modifiable), et pourquoi
//     cette distinction existe
//   - listOf() et mutableListOf() pour créer des listes
//   - size, l'accès par index [0], add() et remove()
//   - parcourir une liste avec for, tester la présence avec in
//   - les lambdas : un bloc { } passé à une fonction, "it" = l'élément
//   - find { }, filter { }, map { } — et sortedBy en simple mention
// =====================================================================

fun main() {

    // ===== 1. LIST : LA LISTE EN LECTURE SEULE =====
    // Une liste se crée avec listOf(...). Kotlin devine tout seul le
    // type des éléments : ici on obtient une List<String>.
    val fruits = listOf("pomme", "banane", "cerise")
    println("Fruits : $fruits")

    // size donne le nombre d'éléments, et [0] lit l'élément à
    // l'index 0 (on compte à partir de 0, comme dans la plupart
    // des langages).
    println("Nombre de fruits : ${fruits.size}")
    println("Premier fruit : ${fruits[0]}")

    // Pourquoi "lecture seule" ? Une List n'a NI add() NI remove().
    // C'est volontaire : si tu passes cette liste à une autre fonction,
    // tu es certain qu'elle ne sera pas modifiée dans ton dos. Même
    // philosophie que val plutôt que var : l'immuable par défaut, le
    // modifiable seulement quand c'est nécessaire.
    // fruits.add("kiwi")   // <- ERREUR de compilation : interdit !

    // ===== 2. MUTABLELIST : LA LISTE MODIFIABLE =====
    // Quand tu as vraiment besoin d'ajouter ou de retirer des éléments,
    // tu crées une MutableList avec mutableListOf(...).
    val courses = mutableListOf("pain", "lait")
    courses.add("oeufs")    // ajoute à la fin de la liste
    courses.remove("lait")  // retire l'élément qui a cette valeur
    println("Courses : $courses (taille : ${courses.size})")

    // ===== 3. PARCOURIR UNE LISTE, ET LE TEST "in" =====
    // for (x in liste) parcourt la liste : à chaque tour de boucle,
    // x prend la valeur de l'élément suivant. Pas d'index à gérer.
    for (fruit in fruits) {
        println("J'aime la $fruit")
    }

    // Le mot-clé in sert aussi à tester si un élément est présent
    // dans une liste : c'est une expression qui vaut true ou false.
    println("banane est dans fruits ? ${"banane" in fruits}")
    println("kiwi est dans fruits ? ${"kiwi" in fruits}")

    // ===== 4. LES LAMBDAS : UN BLOC DE CODE ENTRE { } =====
    // Une lambda, c'est un petit bout de code écrit entre accolades
    // { } et passé à une fonction. La fonction exécute ce bloc pour
    // chaque élément de la liste. À l'intérieur du bloc, "it" désigne
    // l'élément en cours d'examen. C'est tout ce qu'il te faut savoir
    // pour l'instant !
    val nombres = listOf(3, 8, 12, 5, 20)

    // find { } renvoie le PREMIER élément pour lequel la lambda vaut
    // true... ou null si aucun élément ne correspond. Tu reconnais la
    // leçon 4 : le résultat est un Int? (nullable), à sécuriser.
    val premierPair = nombres.find { it % 2 == 0 }
    println("Premier nombre pair : $premierPair")

    // Ici, aucun nombre ne dépasse 100 : find renvoie null, et
    // l'opérateur ?: nous fournit un texte de remplacement.
    val geant = nombres.find { it > 100 }
    println("Premier nombre > 100 : ${geant ?: "aucun trouvé"}")

    // ===== 5. FILTER ET MAP : TRANSFORMER DES LISTES =====
    // filter { } garde TOUS les éléments qui correspondent au test et
    // renvoie une NOUVELLE liste (l'originale n'est pas modifiée).
    val pairs = nombres.filter { it % 2 == 0 }
    println("Tous les nombres pairs : $pairs")

    // map { } transforme CHAQUE élément et renvoie la liste des
    // résultats. Ici : chaque nombre multiplié par 10.
    val parDix = nombres.map { it * 10 }
    println("Multipliés par 10 : $parDix")

    // En bonus, sortedBy { } renvoie une liste triée selon le critère
    // donné par la lambda. Retiens juste que ça existe pour l'instant.
    println("Fruits triés par longueur : ${fruits.sortedBy { it.length }}")

    // ===== À TOI DE JOUER =====
    // Décommente les appels ci-dessous UN PAR UN, écris ton code dans
    // les fonctions plus bas, puis relance le programme pour vérifier.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ----- EXERCICE 1 : la liste de prénoms -----
fun exercice1() {
    // Énoncé : crée une MutableList contenant quelques prénoms.
    // 1) Ajoute 2 prénoms avec add().
    // 2) Retire 1 prénom avec remove().
    // 3) Affiche la liste complète, puis sa taille avec size.
    // Indice : pars de mutableListOf("Alice", "Karim") par exemple.
}

// ----- EXERCICE 2 : find, filter, map -----
fun exercice2() {
    // Énoncé : pars de la liste listOf(3, 8, 12, 5, 20).
    // 1) Avec find { }, trouve le premier nombre strictement
    //    supérieur à 10 et affiche-le.
    // 2) Avec filter { }, garde seulement les nombres pairs et
    //    affiche la liste obtenue.
    // 3) Avec map { }, double tous les nombres et affiche le résultat.
    // Indice : un nombre pair se reconnaît avec it % 2 == 0.
}

// ----- EXERCICE 3 : le mini répertoire -----
fun exercice3() {
    // Énoncé : crée une MutableList de noms (ton répertoire), puis :
    // 1) Écris une fonction chercher(nom: String): String? qui utilise
    //    find { } sur le répertoire et renvoie le nom s'il est présent,
    //    ou null sinon. Tu peux déclarer le répertoire en dehors des
    //    fonctions (tout en haut du fichier) pour que chercher() y ait
    //    accès, ou bien le passer en deuxième paramètre.
    // 2) Appelle chercher() avec un nom présent, puis avec un nom
    //    absent, et affiche un message différent selon le cas :
    //    "trouvé : ..." ou "pas trouvé".
    // Indice : le résultat est un String? -> sécurise-le avec ?: ou
    //    avec if (resultat != null) et le smart cast de la leçon 4.
}
