package lecon03

// ============================================================================
// LEÇON 3 — CONDITIONS ET BOUCLES
// ============================================================================
// Ce que tu vas apprendre :
//  - écrire des conditions avec if / else if / else
//  - utiliser if comme une VALEUR (Kotlin n'a pas de ternaire, if le remplace)
//  - aiguiller entre plusieurs cas avec when (le "switch" de Kotlin)
//  - répéter avec for et les intervalles (1..5), plus downTo et step
//  - répéter avec while
// Lance ce fichier : chaque section affiche son résultat dans la console.
// ============================================================================

fun main() {

    // ===== 1. IF / ELSE IF / ELSE =====
    // Pas de surprise si tu viens d'un autre langage : if exécute son bloc
    // quand la condition entre parenthèses est vraie, else if teste une autre
    // condition, et else attrape tous les cas restants.
    // Une condition se construit avec les comparaisons : == (égal à),
    // != (différent de), < et > (plus petit / plus grand), <= et >= (ou égal).
    // Chaque comparaison produit un Boolean : true ou false.
    val temperature = 23
    if (temperature > 30) {
        println("1) Il fait très chaud !")
    } else if (temperature > 20) {
        println("1) Il fait bon (température : $temperature degrés).")
    } else {
        println("1) Couvre-toi !")
    }

    // ===== 2. IF UTILISÉ COMME UNE VALEUR =====
    // Particularité de Kotlin : if est une EXPRESSION, il PRODUIT une valeur.
    // On peut donc l'écrire directement à droite d'un signe =.
    // C'est pour ça que Kotlin n'a pas d'opérateur ternaire (cond ? x : y) :
    // un if sur une ligne fait exactement le même travail, en plus lisible.
    val a = 12
    val b = 7
    val max = if (a > b) a else b
    println("2) Le plus grand entre $a et $b est $max")

    // ===== 3. WHEN : L'AIGUILLAGE MULTI-CAS =====
    // when compare une valeur à plusieurs cas, comme un switch, mais sans
    // break : dès qu'un cas correspond, son code s'exécute et on sort du when.
    val jour = 6
    when (jour) {
        1 -> println("3) Lundi, courage.")
        2 -> println("3) Mardi.")
        3 -> println("3) Mercredi, le milieu de la semaine.")
        6, 7 -> println("3) C'est le week-end !") // la virgule regroupe plusieurs valeurs
        else -> println("3) Un autre jour.")      // else = le "default" des autres langages
    }

    // Comme if, when peut servir de valeur : chaque flèche renvoie alors
    // quelque chose, et le else devient obligatoire pour couvrir tous les cas.
    val typeDeJour = when (jour) {
        6, 7 -> "un jour de repos"
        else -> "un jour de travail"
    }
    println("3) Le jour numéro $jour est $typeDeJour.")

    // ===== 4. LES INTERVALLES ET LA BOUCLE FOR =====
    // 1..5 crée un intervalle (une "range") : tous les entiers de 1 à 5,
    // bornes INCLUSES. La boucle for parcourt cet intervalle, valeur par
    // valeur, en rangeant chaque nombre dans i.
    print("4) Je compte : ")
    for (i in 1..5) {
        print("$i ") // print (sans "ln") n'ajoute pas de retour à la ligne
    }
    println()
    // À connaître aussi : for (i in 5 downTo 1) descend de 5 à 1, et
    // for (i in 0..10 step 2) avance de 2 en 2 (0, 2, 4, 6, 8, 10).

    // ===== 5. WHILE =====
    // while répète son bloc TANT QUE la condition est vraie. La condition est
    // testée avant chaque tour : pense à faire évoluer ta variable dans le
    // bloc, sinon la boucle ne s'arrête jamais !
    var compteARebours = 3 // var et non val, car la valeur va changer
    while (compteARebours > 0) {
        println("5) Décollage dans... $compteARebours")
        compteARebours = compteARebours - 1
    }
    println("5) Décollage !")

    // ===== À TOI DE JOUER =====
    // Les trois exercices sont des fonctions vides définies après main.
    // Décommente les appels UN PAR UN, écris ton code dans la fonction
    // correspondante, puis relance le programme pour vérifier le résultat.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ===== EXERCICE 1 : la mention d'une note =====
fun exercice1() {
    // Énoncé : écris, juste en dessous de cette fonction, une fonction
    //     fun mention(note: Int): String
    // qui renvoie la mention d'une note sur 20, choisie avec un when :
    //     note < 10   -> "insuffisant"
    //     10 ou 11    -> "passable"
    //     12 ou 13    -> "bien"
    //     14 et plus  -> "très bien"
    // Puis appelle-la ici avec plusieurs notes (8, 11, 13 et 17 par exemple)
    // et affiche chaque résultat avec println.
    // Indice : when (note) avec des virgules pour regrouper 10, 11 d'un côté
    // et 12, 13 de l'autre ; dans le else, un if utilisé comme valeur pour
    // départager "insuffisant" (note < 10) de "très bien" (tout le reste).
}

// ===== EXERCICE 2 : la table de 7 =====
fun exercice2() {
    // Énoncé : affiche la table de multiplication de 7, de 1 à 10, avec une
    // ligne par résultat, sous la forme :
    //     7 x 1 = 7
    //     7 x 2 = 14
    //     ... et ainsi de suite jusqu'à 7 x 10 = 70.
    // Indice : une boucle for (i in 1..10) et un gabarit de chaîne dans le
    // println : "7 x $i = ${7 * i}".
}

// ===== EXERCICE 3 : la somme des nombres pairs =====
fun exercice3() {
    // Énoncé : calcule la somme de tous les nombres PAIRS entre 1 et 100
    // (2 + 4 + 6 + ... + 100), puis affiche le résultat.
    // Résultat attendu : 2550.
    // Indice : déclare var somme = 0, parcours for (n in 1..100) et teste la
    // parité avec le reste de la division entière : n % 2 == 0 signifie que
    // n est pair. Dans ce cas, ajoute n à somme ; affiche somme après la boucle.
}
