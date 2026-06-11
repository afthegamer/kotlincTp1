package lecon02

// =====================================================================
// LEÇON 2 — LES FONCTIONS
// =====================================================================
// Ce que tu vas apprendre :
//   1. Déclarer une fonction : fun nom(param: Type): TypeRetour
//   2. Appeler une fonction et utiliser sa valeur de retour
//   3. Le mot-clé return
//   4. Les fonctions sans valeur de retour
//   5. Les paramètres multiples
//   6. Les valeurs par défaut des paramètres
//   7. (En passant) la forme expression : fun double(x: Int) = x * 2
// Lance ce fichier : chaque section affiche son résultat avec println.
// =====================================================================

fun main() {

    // ===== 1. DÉCLARER ET APPELER UNE FONCTION =====
    // Une fonction se déclare avec le mot-clé "fun", suivi de son nom,
    // de ses paramètres entre parenthèses, puis du type de retour après
    // un deux-points. Ici, "carre" prend un Int et renvoie un Int.
    // (La fonction "carre" est définie plus bas, après main : en Kotlin,
    // l'ordre des déclarations dans un fichier n'a pas d'importance.)
    // Pour l'appeler, on écrit son nom suivi des arguments entre
    // parenthèses. La valeur renvoyée peut être stockée dans une variable.
    val resultat = carre(5)
    println("Le carré de 5 vaut : $resultat")

    // On peut aussi utiliser le retour directement, sans variable.
    println("Le carré de 9 vaut : ${carre(9)}")

    // ===== 2. LE MOT-CLÉ return =====
    // Dans le corps d'une fonction, "return" arrête la fonction et
    // renvoie la valeur indiquée à celui qui l'a appelée.
    // Regarde la fonction "signe" plus bas : elle contient plusieurs
    // return, et le premier rencontré met fin à la fonction.
    println("Signe de 42 : ${signe(42)}")
    println("Signe de -7 : ${signe(-7)}")
    println("Signe de 0 : ${signe(0)}")

    // ===== 3. FONCTION SANS RETOUR =====
    // Une fonction n'est pas obligée de renvoyer quelque chose.
    // Dans ce cas, on ne met simplement pas de type de retour.
    // (Techniquement Kotlin lui donne le type "Unit", l'équivalent
    // de "void" dans d'autres langages, mais on n'a pas à l'écrire.)
    afficherSeparateur()
    println("Ce message est encadré par une fonction sans retour.")
    afficherSeparateur()

    // ===== 4. PARAMÈTRES MULTIPLES =====
    // Une fonction peut prendre plusieurs paramètres, séparés par des
    // virgules. Chaque paramètre a son nom et son type.
    println("3 + 4 = ${additionner(3, 4)}")
    println("10 + 25 = ${additionner(10, 25)}")

    // ===== 5. VALEURS PAR DÉFAUT DES PARAMÈTRES =====
    // En Kotlin, un paramètre peut avoir une valeur par défaut, donnée
    // avec un signe =. Si l'appelant ne fournit pas cet argument,
    // c'est la valeur par défaut qui est utilisée. Très pratique :
    // ça évite d'écrire plusieurs versions de la même fonction.
    println(repeterMot("ho"))          // utilise fois = 3 par défaut
    println(repeterMot("ha", 5))       // ici on fournit fois = 5

    // ===== 6. LA FORME EXPRESSION (mention rapide) =====
    // Quand le corps d'une fonction tient en une seule expression,
    // Kotlin permet d'écrire la fonction sur une ligne, avec un =
    // à la place des accolades et du return. Exemple plus bas :
    // fun double(x: Int) = x * 2
    // Le type de retour est alors déduit automatiquement.
    println("Le double de 21 vaut : ${double(21)}")

    // ===== À TOI DE JOUER =====
    // Les trois fonctions d'exercice sont définies tout en bas du
    // fichier. Écris leur code, puis décommente les appels ci-dessous
    // un par un pour vérifier ton travail.
    // exercice1()
    // exercice2()
    // exercice3()
}

// ===== Fonctions utilisées dans les démonstrations =====

// Déclare une fonction "carre" : un paramètre nommé x de type Int,
// et un type de retour Int (après les deux-points).
fun carre(x: Int): Int {
    return x * x
}

// Plusieurs return sont possibles : le premier atteint termine la fonction.
// (Le "if" teste une condition ; il sera expliqué en détail dans la leçon 3.
// Retiens juste ceci : dès qu'un return est atteint, la fonction s'arrête,
// et le code situé après n'est pas exécuté.)
fun signe(n: Int): String {
    if (n > 0) {
        return "positif"
    }
    if (n < 0) {
        return "négatif"
    }
    return "zéro"
}

// Fonction sans valeur de retour : pas de type après les parenthèses.
fun afficherSeparateur() {
    println("------------------------------")
}

// Deux paramètres, séparés par une virgule.
fun additionner(a: Int, b: Int): Int {
    return a + b
}

// Le paramètre "fois" a une valeur par défaut : 3.
// (repeat est une fonction toute prête des String : "ha".repeat(3) donne
// "hahaha". Pas besoin de boucle ici — les boucles arrivent en leçon 3.)
fun repeterMot(mot: String, fois: Int = 3): String {
    return mot.repeat(fois)
}

// Forme expression : corps en une seule expression, = au lieu des accolades.
fun double(x: Int) = x * 2

// ===== EXERCICES =====

fun exercice1() {
    // ÉNONCÉ : écris ici une fonction aireRectangle(largeur: Double,
    // hauteur: Double): Double qui renvoie l'aire du rectangle.
    // Tu peux la déclarer en dehors de exercice1, en bas du fichier.
    // Puis appelle-la ici et affiche le résultat avec println,
    // par exemple : println("Aire : ${aireRectangle(3.0, 4.5)}")
    // INDICE : l'aire d'un rectangle, c'est largeur * hauteur.
}

fun exercice2() {
    // ÉNONCÉ : écris une fonction saluer(nom: String, politesse: String)
    // qui renvoie une phrase comme "Bonjour, Monsieur Dupont !".
    // Le paramètre politesse doit avoir une valeur par défaut
    // (par exemple "Monsieur"). Appelle-la deux fois ici : une fois
    // sans préciser la politesse, une fois en la précisant.
    // INDICE : valeur par défaut -> politesse: String = "Monsieur",
    // et construis la phrase avec un template "$politesse $nom".
}

fun exercice3() {
    // ÉNONCÉ : écris une fonction estPair(n: Int): Boolean qui renvoie
    // true si n est pair, false sinon. Teste-la ici sur quelques
    // nombres, par exemple : println("4 est pair ? ${estPair(4)}")
    // INDICE : le reste de la division par 2 s'obtient avec n % 2.
    // Un nombre est pair quand ce reste vaut 0, et "ce reste vaut-il 0 ?"
    // s'écrit n % 2 == 0 : le == compare deux valeurs et produit un Boolean
    // (true ou false). Autrement dit, tout tient dans : return n % 2 == 0
}
