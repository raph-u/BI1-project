# Scripts
L'intégralité du code utilisé pour ce projet se trouve sous le répertoire ```DataFetcher\scr\datafetcher```.

## Actor.java
Classe chargée de stocker les données relative aux films et genres de films en relation avec un acteur en particulier.

## DataFetcher.java
Classe de base du projet. C'est en son sein qu'est déclenchée la procédure de récupération des données.

## RequestHandler
Cette classe comporte les différentes méthodes qui sont chargées de lancer des requêtes vers un endpoint spécifique de l'API TMDB.
Ces méthodes sont capables de prendre en charge différents paramètres de requête et retournent le résultat sous forme de String représentant le JSON de réponse qui contient les données d'intérêt.

Les méthodes de RequestHandler attaquent principalement 3 endpoints dont les cas d'usage sont les suivant:
- récupération des genres de film
- récupération des acteurs les plus populaire du moment
- récupération des films

## DataHandler
Classe responsable de parser les fichiers JSON retournés par les méthodes de *RequestHandler*. C'est dans cette classe que sont effectués le filtrage et les traitements de données. Les données récupérées sont stockées dans une instance de type ```Actor```, décrite ci-dessus, de manière à pouvoir facilement les manipuler par la suite.

Les méthodes de cette classe permettent, en autres, de:
- Extraire les genres de film
- Extraire les acteurs
- Calculer le nombre de film dans lequel un acteur a joué
- Calculer le nombre d'occurence de film d'un genre particulier

## DataWriter
Classe chargée de créer les fichier CSV contenant les données extraites des fichiers JSON en se les procurant auprès d'instances de type ```Actor```.

## Fonctionnement général
Lors du lancement du programme, ```DataFetcher```, la classe de base, déclenche la récupération des genres de films afin de pouvoir associer leur ```id``` et leur noms lors de la récupération de données de films. 

Un fois celà fait, le programme récupère la liste des acteurs les plus populaires du moment, puis, l'intégralité des films dans lesquels ils sont impliqués. Un délai de 250ms entre chaque requête est nécessaire pour pouvoir réaliser cette étape, étant donné la limitation de 4 requête/seconde de l'API.

Dès que les films sont récupérés, une association entre ces derniers et les genres est faite, puis, le nombre de film par genre ainsi que le nombre de films total est calculé pour chaque acteur.

Ensuite, les données sont écrites dans 3 fichiers CSV.