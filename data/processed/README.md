# Données traitées
Les données générées sont stockées dans 3 fichiers CSV distincts. La liaison entre chacun d'entre-eux se fait via la champ *Acteur*.
La classe responsable de leur génération est ```DataWriter.java```. Cette dernière possède 3 méthodes, chacune responsable de générer un fichier.

## genres.csv
Fichier généré par: ```DataWriter.writeGenresToCSV()```.
Contient la liste de genre et le nombre de films dans lesquels un acteur à joué pour un genre en particulier.

### Structure des données
| Variable      | Type          |
| ------------- |:-------------:|
| Actor         | Chaîne de caractères |
| Action        | Numérique |
| Adventure     | Numérique |
| Animation     | Numérique |
| Comedy        | Numérique |
| Crime         | Numérique |
| Documentary   | Numérique |
| Drama         | Numérique |
| Family        | Numérique |
| Fantasy       | Numérique |
| History       | Numérique |
| Horror        | Numérique |
| Music         | Numérique |
| Mystery       | Numérique |
| Romance       | Numérique |
| Science       | Numérique |
| TV Movie      | Numérique |
| Thriller      | Numérique |
| War           | Numérique |
| Western       | Numérique |


## totals.csv
Fichier généré par: ```DataWriter.writeTotalsToCSV()```.
Contient le nombre total de films dans lesquels un acteur a joué.

### Structure des données
| Variable      | Type          |
| ------------- |:-------------:|
| Actor         | Chaîne de caractères |
| Movie total   | Numérique |

## popular.csv
Fichier généré par: ```DataWriter.writePopularToCSV()```.
Contient le genre du film le plus populaire dans lequel un acteur a joué ainsi que son genre.

### Structure des données
| Variable                 | Type          |
| -------------            |:-------------:|
| Actor                    | Chaîne de caractères |
| Total for genre          | Numérique |
| Most popular movie genre | Chaîne de charactères |

