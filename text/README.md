# Projet BI1

## Reprise / exécution du projet
### Reprise du projet
1. Télécharger le repo.
2. Extraire le répertoire *DataFetcher* situé sous ```code\scripts``` dans un dossier de votre choix.
3. Placer un fichier nommé ```apiKey.txt``` à la racine du répertoire *DataFetcher* de l'étape précédente.
Ce fichier doit contenir une clé d'API TMDB, essentielle au fonctionnement du programme.
4. Ouvrir *DataFetcher* dans l'IDE / éditeur de texte de votre choix.

Note: Les fichiers générés suite à la récupération de données de l'API apparaitront à la racine du répertoire  ```DataFetcher```.

### Exécution du programme (passage au Tidy data)
1. Télécharger le repo.
2. Copier le répertoire *dist* situé sous ```code\scripts\DataFetcher``` dans un dossier de votre choix.
3. Placer un fichier nommé ```apiKey.txt``` à la racine du répertoire *dist* de l'étape précédente.
Ce fichier doit contenir une clé d'API TMDB, essentielle au fonctionnement du programme.
4. Exécuter ```DataFetcher.jar```.

Note: L'exécution de ```DataFetcher.jar``` peut durer plusieurs secondes en fonction de la quantité de données récupérée.
Une fois l'exécution achevée, trois fichiers CSV contenant les données obtenue via l'API apparaissent dans le répertoire.

## Introduction
L'idée derrière ce projet était de satisfaire ma curiosité quant aux aspects suivants:
Un acteur a-t-il une catégorie de film dominant ? Il est commun de voir jouer des acteurs dans un genre de film précis et des les associer avec, par exemple, des films d'action. Cependant, les acteurs les plus connus sont-ils réellement lié à une catégorie particulière ?
Dans quelle catégorie se classe le film le plus populaire dans lequel un acteur a joué et celui-ci est il dans la même catégorie que la majeur partie de ses films ?

## Méthodes
### Provenance des données
L'intégralité des données utilisées dans le cadre de ce projet provient de l'API [The movie DB](https://www.themoviedb.org/)
`The movie DB` est propulsé et maintenu par la communauté et met à disposition les données de plus de 300'000 films différents.
L'API et les données à disposition sont totalement libre d'accès pour chacun mais requiert cependant la création d'un compte permettant de récupérer une clé donnant accès à l'API. Il est aussi important que le nombre de requêtes est limité à un maximum de 4 par seconde.

## Résultats
pour des raisons de visibilités, les graphiques se basent sur un nombre de 20 acteurs.
![Nombre de films par acteur et par genres](../figures/final/genres.png "Nombre de films par acteur et par genres")
![Nombre de film le plus populaire par acteur et par genre](../figures/final/popular.png "Nombre de film le plus populaire par acteur et par genre")
![Nombre de film le plus populaire en comparaison avec le nombre total de film par acteur](../figures/final/totals.png "Nombre de film le plus populaire en comparaison avec le nombre total de film par acteur")

## Conclusion
On remarque dans beacoup de cas que la catégorie de film la plus populaire pour un acteur n'est pas celle dans laquelle il a joué le plus de film. De plus, le nombre de films de cette catégorie est très faible en comparaison avec le nombre total de films joués par l'acteur.