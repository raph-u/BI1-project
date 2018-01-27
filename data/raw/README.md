# RAW
Etant donné que ce projet se base sur l'API de TMDB, il ne possède pas de "raw data" à proprement parler.
Néanmoins, voici les sources exactes dont proviennent les données ainsi que leur date de récupération.

## Source des données
L'intégralité des données provient de l'API TMDB.
Dernière date de récupération: 23.01.18.

- Site de TMDB
- Documentation de l'API

## Récupération des genres
URL:
https://api.themoviedb.org/3/genre/movie/list?api_key=<clé de l'API>

Données retournées (forma JSON):

## Récupération des acteurs les plus populaires du moment
URL:
https://api.themoviedb.org/3/person/popular?api_key=<clé de l'API>&sort_by=popularity.desc

Données retournées (forma JSON):

# Récupération des films associés à un acteur
URL:
https://api.themoviedb.org/3/discover/movie?api_key=<clé de l'API>&with_cast=<ID d'un acteur>

Données retournées (forma JSON):


Note: il n'est pas possible de récupérer l'intégralité des films dans lequel joue un acteur en une seule requête.
Pour pouvoir le faire, il est nécessaire de sintéressé aux paramètres suivants retournés dans le JSON décrit ci-dessus:
- ```page```
- ```total_pages```

```total_pages``` contient le nombre total de pages contenant des films.
Il faut donc s'en servir en combinaison avec ```page```, qui indique la page actuellement récupérée pour retrouver la totalité des films