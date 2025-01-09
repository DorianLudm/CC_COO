# Explication des patterns

## Factory
L'ensemble des biomes disponibles contiennent des familles d'objets. Ce design pattern permet de facilement centraliser le comportement de chaque objet, et d'ensuite préciser le comportement selon la famille, si nécessaire.  

## State
Les états des animaux possèdent tous un comportement similaire selon des états similaire.  
On utilise ce pattern par le fait que l'animal possède un ensemble de comportement dans lequel il peut transitionner en fonction de son environnement et qu'il possède des comportements différent pour chaque état qu'il possède.  

## Singleton
Utilisé sur les classes "Player" et "Map". On notera par ailleurs que les états des animaux pourrait aussi en avoir.  
Ce pattern permet de s'assurer qu'une seule instance de Player et de Map existe au sein du processus du jeu, en plus de permettre de facilement récuper celles-ci.  

## Commande
Gestion du retour dans le temps avec le pattern Commande en 2 classe GameTurnCommand et GameTurnInvocator  
GameTurnCommand se charge de sauvegarder une copie de la réprésentation de la map et la position du joueur.  