#Présentation des fichiers ODT

## Présentation Général

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/open-source.svg)](https://forthebadge.com)

### Le content.xml

Le content.xml est le fichier racine, ce fichier contient l'inforamtion en elle-même. 
Dans ce fichier les données binarisées binarisées ne sont pas présent.

### Le Style.xml

Le fichier Style.xml contient les inforamtion statiques comme la coueleur, la taille de police, la hauteur de la page, la mise en forme des listes.
Le fichier style est important pour ODF car il sert exclussivement de ce fichier pour faire la mise en page du document et le reproduire comme il
a été crée.

### Le setting.xml

Ce fichier contient les réglages fixés par l'utilisateur lors de la sauvergarde du fichier.

### Le meta.xml

Le mtéa continent les métadonnée stockées et mises à jour dans le fichier.
Voici des exmeple de métadonnée la date de création, la date de dernière modification, la langage utilisé, l'auteru ...

### Minetype

Ce fichier comporte que une ligne sans extension qui précise le type MIME du fichier.
Le type MIME est le type de médias. Il est un identifiant de format de données sur internet en deux parties.

### META-INF/manifest.xml

Le manifest est un fichier contenant la liste de l'emnsemble des ficheir contenu dans l'archive. 
Il existe aussi un manifest dans l'archive JAR en JAVA.

### Pictures

Le dossier picture contient la globalité des images présentes dans les docments présent dans l'archive. 
Elles seront référencés directement au format image souvent en PNJ.

## Le fichier META en détail

### Quels est le rôle du fichier meta.xml?

Le fichier meta contient l'intégralité des __métadonnées__ associées à son document.

### Que trouves-t-on à l'intérieur?

A l'intérieur, nous trouvons une liste de champs prédéfinis assez standard: 
<ul>
    <li>Application</li>
    <li>Titre</li>
    <li>Description</li>
    <li>Sujet</li>
    <li>Mots-clés</li>    
    <li>auteur initial</li>
    <li>auteur</li>
    <li>imprimé par</li>
    <li>date de création </li>
    <li>date de dernière modification</li>
    <li>date de dernière impression</li>
    <li>durée d'édition</li>
    <li>modèle utilisé</li>
    <li>rechargement automatique </li>
    <li>langue</li>
    <li>nombre d'éditions</li>
    <li>durée totale d'édition</li>
    <li>statistiques sur le document</li>
</ul>

### Que fait-il?

Il permet de géré interfonctionnement informatiques entre divers types de ressources informatique.

## Le fichier CONTENT en détail

### Quels est le rôle du fichier meta.xml?

### Que trouves-t-on à l'intérieur?

### Que fait-il?