# Présentation des fichiers ODT

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/open-source.svg)](https://forthebadge.com)

## Présentation Général

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

### La racine

Le fichier varie celon les différents format d'enregistrement mais il utilise toujours les mêmes balises.
La racine sera toujours nommée comme cela ```<office:document-content espace:de:nom>```.

### Que peut-on trouvé dans l'espace nom?

Dans l'espaces nom, nous pouvons trouvé les informations suivantes:

<ol>
  <li>Texte
    <ul>
      <li>```urn:oasis:names:tc:opendocument:xmlns:text:1.0```</li>
     </ul>
   </li>
   <li>Présentation
    <ul>
      <li>```urn:oasis:names:tc:opendocument:xmlns:presentation:1.0```</li>
     </ul>
   </li>
   <li>Feuille de style 
    <ul>
      <li>```urn:oasis:names:tc:opendocument:xmlns:style:1.0```</li>
     </ul>
   </li>
   <li>Fichier MathML
    <ul>
      <li>[texte du lien](http://www.w3.org/1998/Math/MathML "Espace de noms MathML")</li>
     </ul>
   </li>
   <li>Texte OOo :
    <ul>
      <li>[texte du lien](http://openoffice.org/2004/writer "Apache OpenOffice 4.1.9")</li>
     </ul>
   </li>
</ol>

### Que peut-on trouvé d'autre?

<ol>
  <li>Macros
    <ul>
      <li>```<office:scripts>```</li>
     </ul>
   </li>
   <li>Polices utilisées
    <ul>
      <li>```<office:font-face-decls> ```</li>
     </ul>
   </li>
   <li>Styles internes
    <ul>
      <li>```<office:styles>```</li>
     </ul>
   </li>
</ol>

### Conclusion

Les élents cité dans la partie précendente sont toujours en dernier.
L'élement ```<office:body>``` est défini par son premier sous-élement , qu'on retrouver dans la 2e partie de cette sections.
Pour un document texte, le sous-élement sous la balise ```<office:body>``` sera la la balise ```<office:text>```.