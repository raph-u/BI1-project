# Processed
Les données générées sont stockées dans 3 fichiers CSV distincts. La liaison entre chacun d'entre-eux se fait via la champ *Acteur*.
La classe responsable de leur génération est ```DataWriter.java```. Cette dernière possède 3 méthodes, chacune responsable de générer un fichier.

## genres.csv
Fichier généré par: ```writeGenresToCSV()```.
Contient la liste de genre et le nombre de films dans lesquels un acteur à joué pour un genre en particulier.

## totals.csv
Fichier généré par: ```writeTotalsToCSV()```.
Contient le nombre total de films dans lesquels un acteur a joué.

## popular.csv
Fichier généré par: ```writePopularToCSV()```.
Contient le genre du film le plus populaire dans lequel un acteur a joué ainsi que son genre.
