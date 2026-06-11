package lecon04

// =====================================================================
// LEÇON 4 — LE NULL SOUS CONTRÔLE (LA grande idée de Kotlin)
// =====================================================================
//
// Ce que tu vas apprendre :
//   - ce qu'est null, et pourquoi il fait planter les autres langages
//   - String et String? sont DEUX TYPES DIFFÉRENTS
//   - l'appel sécurisé ?.   (texte?.length)
//   - l'opérateur Elvis ?:  (une valeur de secours quand c'est null)
//   - le passage en force !! et pourquoi il est dangereux
//   - le smart cast : if (x != null) { ... }
//   - toIntOrNull() : convertir un texte en nombre sans risque
//   - readlnOrNull() : la lecture clavier, qui peut renvoyer null
//
// Lance ce fichier et lis les commentaires section par section :
// chaque démonstration affiche son résultat dans la console.
// =====================================================================

// Petite fonction pour les démos : elle renvoie un String? car elle peut
// échouer. C'est comme ça que null débarque dans la vraie vie : une
// recherche qui ne trouve rien, un fichier absent, une saisie vide...
fun surnomDe(prenom: String): String? {
    if (prenom == "Robert") return "Bob"
    return null // pas de surnom connu pour les autres
}

fun main() {
    // ===== 1. C'EST QUOI, NULL ? =====
    // null signifie "absence de valeur" : la case est vide.
    // Dans beaucoup de langages (Java, C#, JavaScript...), N'IMPORTE
    // QUELLE variable peut valoir null. Si tu l'utilises sans y penser,
    // le programme PLANTE en pleine exécution : c'est la fameuse
    // NullPointerException. Tony Hoare, l'informaticien qui a inventé la
    // référence null en 1965, a lui-même qualifié son invention
    // d'"erreur à un milliard de dollars".
    // Kotlin règle le problème autrement : il détecte le danger
    // À LA COMPILATION, avant même que le programme ne démarre.
    println("--- 1. null, l'absence de valeur ---")

    // ===== 2. String ET String? : DEUX TYPES DIFFÉRENTS =====
    // En Kotlin, une variable de type String ne peut JAMAIS valoir null.
    // Pour autoriser null, il faut le déclarer avec un ? : String?.
    println("--- 2. String vs String? ---")
    val prenom: String = "Zoé"             // garanti non-null, pour toujours
    val surnom: String? = surnomDe(prenom) // peut être null : le type le dit
    println("prenom = $prenom")
    println("surnom = $surnom") // affiche "null" : println sait l'afficher
    // val interdit: String = null  // NE COMPILE PAS : String refuse null

    // ===== 3. LE COMPILATEUR VEILLE AU GRAIN =====
    // Sur un String?, impossible d'appeler directement .length :
    // println(surnom.length)  // NE COMPILE PAS !
    // Erreur : "only safe (?.) or non-null asserted (!!.) calls are allowed"
    // Le compilateur te FORCE à prendre une précaution. C'est LA grande
    // idée de Kotlin : le plantage potentiel est bloqué avant l'exécution.

    // ===== 4. L'APPEL SÉCURISÉ ?. =====
    // texte?.length signifie : "si texte n'est pas null, donne-moi sa
    // longueur ; sinon, donne-moi null". Aucun plantage possible.
    println("--- 4. L'appel sécurisé ?. ---")
    val surnomRobert: String? = surnomDe("Robert") // vaudra "Bob"
    println("Longueur du surnom de Robert : ${surnomRobert?.length}") // 3
    println("Longueur du surnom de Zoé : ${surnom?.length}")          // null
    // Attention : le résultat de surnom?.length est de type Int? (un Int
    // qui peut être null). La précaution se propage, c'est voulu.

    // ===== 5. L'OPÉRATEUR ELVIS ?: =====
    // a ?: b signifie : "prends a, et si a est null, prends b à la place".
    // (Penche la tête à gauche : ?: ressemble à la coiffure d'Elvis.)
    println("--- 5. L'opérateur Elvis ?: ---")
    val surnomAffichable = surnom ?: "(pas de surnom)"
    println("Surnom affichable : $surnomAffichable")
    // Combo très courant : appel sécurisé PUIS valeur de secours.
    val longueurSure = surnom?.length ?: 0
    println("Longueur (0 si null) : $longueurSure")

    // ===== 6. LE PASSAGE EN FORCE !! (À ÉVITER) =====
    // texte!! dit au compilateur : "fais-moi confiance, ce n'est pas null".
    // Si tu te trompes, le programme plante avec une NullPointerException,
    // exactement le mal que Kotlin cherche à éradiquer !
    println("--- 6. Le passage en force !! ---")
    val force: String = surnomRobert!! // toléré ICI : on sait que c'est "Bob"
    println("Passage en force : $force")
    // println(surnom!!)  // aurait PLANTÉ le programme : surnom est null !
    // Règle de survie : chaque fois que tu écris !!, demande-toi si
    // ?. ou ?: ne ferait pas mieux l'affaire. La réponse est souvent oui.

    // ===== 7. LE SMART CAST : if (x != null) =====
    // Si tu vérifies toi-même qu'une valeur n'est pas null, le compilateur
    // s'en souvient : DANS le bloc, le String? est traité comme un String.
    // C'est le "smart cast" (conversion intelligente de type).
    println("--- 7. Le smart cast ---")
    val surnomAlice: String? = surnomDe("Alice")
    if (surnomAlice != null) {
        // Ici, surnomAlice est un String : .length direct, sans ?. !
        println("Alice a un surnom de ${surnomAlice.length} lettres")
    } else {
        println("Alice n'a pas de surnom connu")
    }

    // ===== 8. toIntOrNull() : UNE CONVERSION QUI PEUT ÉCHOUER =====
    // Convertir un texte en nombre peut rater ("douze" n'est pas un Int).
    // Plutôt que de planter, toIntOrNull() renvoie un Int? :
    // le nombre si ça marche, null sinon.
    println("--- 8. toIntOrNull() ---")
    val reussi: Int? = "123".toIntOrNull()
    val rate: Int? = "douze".toIntOrNull()
    println("\"123\".toIntOrNull()   -> $reussi")
    println("\"douze\".toIntOrNull() -> $rate")
    println("Avec valeur de secours : ${rate ?: -1}")

    // ===== 9. AU PASSAGE : readlnOrNull() =====
    // Pour lire ce que tape l'utilisateur au clavier, Kotlin fournit
    // readlnOrNull() : elle renvoie un String?, car la lecture peut
    // échouer (fin de l'entrée, flux fermé...). Tous les outils de cette
    // leçon serviront donc dès que tu liras le clavier, par exemple :
    //     val saisie: String? = readlnOrNull()
    //     val nombre = saisie?.toIntOrNull() ?: 0
    // On ne l'exécute pas ici, pour que la leçon tourne sans interaction.

    // ===== À TOI DE JOUER =====
    // Écris ton code dans les fonctions exercice1/2/3 plus bas, puis
    // décommente les appels UN PAR UN et relance pour vérifier.
    // exercice1()
    // exercice2()
    // exercice3()
}

fun exercice1() {
    // ÉNONCÉ : écris une fonction longueurDe(texte: String?): Int qui
    // renvoie la longueur du texte, ou 0 si le texte est null.
    // Déclare-la en dehors de exercice1(), puis teste-la ici :
    //     println(longueurDe("Kotlin"))  // doit afficher 6
    //     println(longueurDe(null))      // doit afficher 0
    // INDICE : tout tient en une ligne avec ?. et ?: (revois la section 5).
}

fun exercice2() {
    // ÉNONCÉ : écris une fonction crier(texte: String?) qui affiche le
    // texte en MAJUSCULES, ou "(rien à dire)" si le texte est null.
    // Teste-la ici :
    //     crier("au secours")  // doit afficher AU SECOURS
    //     crier(null)          // doit afficher (rien à dire)
    // INDICE : .uppercase() met un String en majuscules ; combine
    // l'appel sécurisé ?. et l'opérateur Elvis ?: pour le reste.
}

fun exercice3() {
    // ÉNONCÉ : convertis la chaîne "42abc" puis la chaîne "42" en nombre
    // avec toIntOrNull(). Pour chacune, affiche un message différent :
    //     - conversion échouée (null) : "Conversion impossible"
    //     - conversion réussie        : "J'ai obtenu le nombre 42"
    // INDICE : range chaque résultat dans un val (de type Int?), puis
    // choisis ton arme : smart cast (if (n != null) ...) ou Elvis ?:.
}
