# Parcours Kotlin — les bases, en autonomie

Un parcours d'apprentissage Kotlin pour grand débutant : chaque leçon est un fichier `.kt`
**commenté** (le cours est écrit dans le code) suivi de **3 exercices** à compléter.

## Comment travailler une leçon

1. Ouvre le fichier de la leçon dans `src/`.
2. **Lis-la de haut en bas** : les commentaires SONT le cours.
3. Lance sa fonction `main()` (le triangle vert ▶ dans IntelliJ) et regarde la console :
   chaque section affiche son résultat, à lire en parallèle des explications.
4. Descends aux **3 exercices** en bas du fichier. Pour chacun : écris ton code dans la
   fonction (là où c'est indiqué `// (à toi d'écrire ton code ici)`), puis **décommente son
   appel** dans `main` (`// exercice1()` → `exercice1()`) et relance.

Astuce ligne de commande (facultatif) : `kotlinc src -d out` compile tout le dossier d'un coup.

## Ordre de lecture (suis les numéros)

| # | Fichier | Ce que tu apprends |
|---|---------|--------------------|
| 1 | `src/lecon01/Variables.kt` | Variables (`val`/`var`), types, inférence, arithmétique, templates de chaînes |
| 2 | `src/lecon02/Fonctions.kt` | Déclarer/appeler des fonctions, `return`, paramètres, valeurs par défaut |
| 3 | `src/lecon03/ConditionsEtBoucles.kt` | `if`/`else`, `when`, intervalles, boucles `for` et `while` |
| 4 | `src/lecon04/LeNull.kt` | Le null sous contrôle : `String?`, `?.`, `?:`, `!!`, smart cast, `toIntOrNull` |
| 5 | `src/lecon05/ClassesEtObjets.kt` | Classes et objets, propriétés, méthodes, `this`, `toString`, `companion object` |
| 6 | `src/lecon06/Collections.kt` | `List` vs `MutableList`, parcours, lambdas, `find`/`filter`/`map` |
| 7 | `src/lecon07/InterfacesEtGeneriques.kt` | Interfaces (contrats), polymorphisme, génériques `<T>` |
| 8 | `src/lecon08/TableauxEtSaisie.kt` | Tableaux (`Array`) et saisie clavier (`readln` / `readlnOrNull`) |
| 9 | `src/lecon09/HeritageEtAbstraction.kt` | Héritage (`open`/`override`/`super`), classes `abstract`, `is`/`as` |
| 10 | `src/lecon10/MapSetEnum.kt` | `Set` (sans doublons), `Map` (clé→valeur), `enum class` |

> Les leçons se lisent **dans l'ordre** : chaque leçon ne s'appuie que sur les précédentes.
> La sécurité du null (leçon 4) est vue tôt, car elle resurgit partout ensuite.

## À venir

- **Projets** de synthèse (gestion de véhicules, mini-bibliothèque) une fois les 10 leçons acquises.
- **Bloc Android / Jetpack Compose** : la suite, après les bases Kotlin.

## État

Les 10 leçons compilent (kotlinc 2.4.0, 0 erreur). Les corps d'exercices sont laissés vides
exprès : c'est à toi de les écrire.
