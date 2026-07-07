package lecon08

// ============================================================================
// LEÇON 8 — TABLEAUX ET SAISIE AU CLAVIER
// ============================================================================
// (à faire après les leçons 3 (boucles) et 4 (null safety))
//
// Ce que tu vas apprendre :
//  - créer un tableau : arrayOf("a","b","c") et les variantes typées
//    intArrayOf(1,2,3), doubleArrayOf(1.5, 2.0)
//  - la taille avec .size, l'accès par index tableau[0] (on compte à partir de 0)
//  - modifier une case : tableau[0] = ... (même déclaré en val !)
//  - parcourir : for (x in tableau), for (i in tableau.indices), indexOf, in
//  - la différence entre un Array (taille FIXE) et une List (leçon 6)
//  - poser une question avec print(...) et lire la réponse avec readln()
//  - lire sans risque avec readlnOrNull() (String?), convertir avec
//    toIntOrNull() / toDoubleOrNull() et sécuriser avec l'opérateur Elvis ?:
// Lance ce fichier : chaque section affiche son résultat dans la console.
// ============================================================================

fun main() {

    // ===== 1. CRÉER UN TABLEAU =====
    // Un tableau (Array) range PLUSIEURS valeurs dans une seule variable, au
    // lieu de créer une variable par valeur. On le crée avec arrayOf(...) en
    // listant les valeurs entre parenthèses, séparées par des virgules.
    // joinToString() colle les éléments en un seul texte pour l'affichage
    // (par défaut il les sépare par ", ").
    val fruits = arrayOf("pomme", "banane", "cerise")
    println("1) Mon tableau de fruits : ${fruits.joinToString()}") // pomme, banane, cerise

    // Pour des nombres, Kotlin fournit des variantes TYPÉES, plus efficaces :
    // intArrayOf(...) pour des Int, doubleArrayOf(...) pour des Double.
    val nombres = intArrayOf(10, 20, 30, 40)
    val prix = doubleArrayOf(1.5, 2.0, 3.75)
    println("1) Des entiers : ${nombres.joinToString()}") // 10, 20, 30, 40
    println("1) Des doubles : ${prix.joinToString()}")     // 1.5, 2.0, 3.75

    // ===== 2. LA TAILLE (.size) ET L'ACCÈS PAR INDEX =====
    // .size donne le nombre d'éléments. Pour lire une case précise, on écrit
    // le nom du tableau suivi de son NUMÉRO D'INDEX entre crochets : fruits[0].
    // ATTENTION : on compte à partir de 0. La première case est [0], la
    // deuxième [1], etc. Donc la DERNIÈRE case est à l'index .size - 1.
    println("2) Il y a ${fruits.size} fruits dans le tableau.") // 3
    println("2) Case 0 (le premier) : ${fruits[0]}")            // pomme
    println("2) Case 1 (le deuxième) : ${fruits[1]}")           // banane
    println("2) Case ${fruits.size - 1} (le dernier) : ${fruits[fruits.size - 1]}") // Case 2 (le dernier) : cerise

    // ===== 3. MODIFIER UNE CASE (même en val !) =====
    // Point qui surprend souvent : fruits est déclaré en val, et pourtant on
    // peut CHANGER le contenu d'une case avec fruits[0] = ... . C'est le même
    // principe qu'avec les collections MUTABLES de la leçon 6 : val interdit
    // de réaffecter la VARIABLE (fruits = autreChose est refusé), mais le
    // CONTENU du tableau, lui, reste modifiable.
    fruits[0] = "abricot"
    println("3) Après fruits[0] = \"abricot\" : ${fruits.joinToString()}") // abricot, banane, cerise

    // ===== 4. PARCOURIR UN TABLEAU =====
    // Deux grandes façons de faire le tour d'un tableau (revois la leçon 3
    // sur le for). Le tableau contient maintenant : abricot, banane, cerise.

    // a) for (x in tableau) : x prend directement la VALEUR de chaque case.
    print("4a) Chaque fruit : ")
    for (fruit in fruits) {
        print("$fruit ") // print (sans "ln") reste sur la même ligne
    }
    println() // saut de ligne final

    // b) for (i in tableau.indices) : i prend chaque INDEX (0, 1, 2...).
    // .indices est l'intervalle des index valides. Pratique quand tu as
    // besoin du numéro de la case en plus de sa valeur.
    for (i in fruits.indices) {
        println("4b) Index $i -> ${fruits[i]}")
    }
    // Résultat :
    // 4b) Index 0 -> abricot
    // 4b) Index 1 -> banane
    // 4b) Index 2 -> cerise

    // indexOf te donne l'index d'une valeur (ou -1 si elle est absente).
    println("4c) indexOf(\"banane\") = ${fruits.indexOf("banane")}") // 1
    println("4c) indexOf(\"kiwi\")   = ${fruits.indexOf("kiwi")}")   // -1

    // ===== 5. TABLEAU (Array) vs LISTE (List) =====
    // En une phrase : un Array a une TAILLE FIXE (impossible d'ajouter ou de
    // supprimer une case après sa création), alors qu'une List de la leçon 6
    // (surtout en version mutable) peut grandir et rétrécir avec add/remove.
    // L'opérateur in teste au passage la présence d'une valeur, comme sur les
    // collections.
    println("5) \"banane\" est dans le tableau ? ${"banane" in fruits}") // true
    println("5) \"kiwi\" est dans le tableau ?   ${"kiwi" in fruits}")   // false

    // ===== 6. LA SAISIE AU CLAVIER : print + readln =====
    // Pour dialoguer avec l'utilisateur : print(...) affiche la QUESTION sans
    // sauter de ligne (le curseur reste juste après), puis readln() attend que
    // l'utilisateur tape une ligne et appuie sur Entrée, et renvoie ce qu'il a
    // tapé sous forme de String. Exemple, COMMENTÉ car readln() BLOQUE le
    // programme tant qu'on n'a rien tapé (on veut que cette leçon tourne
    // toute seule, sans interaction) :
    //     print("Comment tu t'appelles ? ")
    //     val nom = readln()            // renvoie un String (jamais null)
    //     println("Bonjour, $nom !")
    println("6) (démo de readln commentée dans le code : lis les commentaires)")

    // ===== 7. LIRE SANS RISQUE : readlnOrNull() + toIntOrNull() + Elvis =====
    // readlnOrNull() est la version prudente de readln() : elle renvoie un
    // String? (rappel leçon 4 : la lecture PEUT échouer et donner null, par
    // exemple en fin de flux). De plus, ce qui est tapé est TOUJOURS du texte :
    // pour obtenir un nombre il faut le convertir. toIntOrNull() (leçon 4)
    // renvoie un Int? : le nombre si le texte est convertible, null sinon.
    // On sécurise le tout avec l'opérateur Elvis ?: qui fournit une valeur de
    // secours. Le schéma complet d'une lecture d'entier ressemblerait à ça
    // (COMMENTÉ, car il lit le clavier) :
    //     print("Ton âge ? ")
    //     val texte: String? = readlnOrNull()      // String? : peut être null
    //     val age: Int = texte?.toIntOrNull() ?: 0 // 0 si null OU non convertible
    //     println("Âge retenu : $age")
    //
    // Pour VOIR la conversion tourner sans clavier, on remplace la lecture par
    // des chaînes littérales (comme si l'utilisateur les avait tapées) :
    val ageSaisi = "42".toIntOrNull() ?: 0
    println("7) \"42\".toIntOrNull() ?: 0    -> $ageSaisi")   // 42
    val ageRate = "abc".toIntOrNull() ?: 0
    println("7) \"abc\".toIntOrNull() ?: 0   -> $ageRate")    // 0
    val prixSaisi = "3.75".toDoubleOrNull() ?: 0.0
    println("7) \"3.75\".toDoubleOrNull() ?: 0.0 -> $prixSaisi") // 3.75

    // ===== À TOI DE JOUER =====
    // Les trois exercices sont des fonctions vides définies après main.
    // Décommente les appels UN PAR UN, écris ton code dans la fonction
    // correspondante, puis relance le programme pour vérifier le résultat.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ===== EXERCICE 1 : la somme des éléments d'un tableau =====
fun exercice1() {
    // Énoncé : on te donne le tableau
    //     val notes = intArrayOf(12, 8, 15, 10, 5)
    // Calcule la SOMME de tous ses éléments, puis affiche-la avec println.
    // Résultat attendu : 50.
    // Indice : déclare var somme = 0, puis parcours le tableau avec
    // for (n in notes) et ajoute n à somme (somme = somme + n, ou somme += n).
    // Affiche somme APRÈS la boucle.
    // (à toi d'écrire ton code ici)
}

// ===== EXERCICE 2 : trouver le maximum d'un tableau =====
fun exercice2() {
    // Énoncé : on te donne le tableau
    //     val temperatures = intArrayOf(18, 25, 12, 30, 22)
    // Trouve la PLUS GRANDE valeur du tableau, puis affiche-la.
    // Résultat attendu : 30.
    // Indice : déclare var max = temperatures[0] (on part de la première
    // case), puis parcours le reste avec for (t in temperatures) ; à chaque
    // tour, si t > max, mets max à jour (max = t). Affiche max à la fin.
    // (à toi d'écrire ton code ici)
}

// ===== EXERCICE 3 : une saisie sécurisée (sans vraie lecture) =====
fun exercice3() {
    // Énoncé : simule une saisie clavier avec une chaîne littérale, comme dans
    // la section 7 (ne lance PAS de vraie lecture). Pars de :
    //     val saisie: String? = "7"   // fais comme si l'utilisateur avait tapé 7
    // Convertis saisie en Int de façon sécurisée (résultat 0 si null ou non
    // convertible), range le dans un val quantite, puis affiche :
    //     "Tu as commandé X article(s)"   (X étant la quantité obtenue)
    // Avec "7", ça doit afficher : Tu as commandé 7 article(s).
    // Teste ensuite en remplaçant "7" par "xyz" : tu dois obtenir 0.
    // Indice : combine l'appel sécurisé ?. , toIntOrNull() et l'Elvis ?:
    // sur une seule ligne : saisie?.toIntOrNull() ?: 0 (revois la leçon 4 et la section 7 ci-dessus).
    // (à toi d'écrire ton code ici)
}
