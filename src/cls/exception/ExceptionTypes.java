package cls.exception;

public enum ExceptionTypes
{
    // enumération
    TYPE_ARGUMENT("Le ou les arguments passes en parametre sont invalides\n\t-t pour les fichiers odt\n\t-s pour les fichiers ods\n\t-p pour les fichiers ods\n\t[PATH] sans parametre sinon\nVoir la documentation\n<?> java -jar odfanalyser.jar"),
    INVALID_PATH("Le path vers les fichiers odf est absent ou mal renseigne\n\tCela peut etre du aux arguments <-t>, <-s> ou <-p>\n\tA un mauvais chemin\n\tOu a des caracteres speciaux (dans ce cas, utiliser les guillets)\nVoir la documentation\n<?> java -jar odfanalyser.jar"),
    INVALID_OPTION("Les options de l'execution sont invalides\n\t-v verbose\n\t-x [EXTRACT PATH] extraction\tle path est facultatif\n\tou -vx [EXTRACT PATH]\nVoir la documentation\n<?> java -jar odfanalyser.jar"),
    FILE_DOES_NOT_EXIST("Vous essayez d'executer odfanalyser sur un fichier qui n'existe pas\nou est inaccessible"),
    NEEDED_PATH("L'un des arguments <-t>, <-s> ou <-p> n'est pas suivi d'un chemin direct\nvers les fichiers a analyser\nVoir la documentation\n<?> java -jar odfanalyser.jar"),
    EXTENSION("L'extension renseignee est invalide ou indefinie\nElle ne fait pas partie de la suite OpenDocument Format\nou n'a pas ete specifie\n\nVoir la documentation\n<?> java -jar odfanalyser.jar"),
    ODFFILE_FAILED("L'extraction ou la redirection vers le menu approprie a echoue\n\tCela peut etre du a une exception de flux\nVerifiez en particulier :\n\tle chemin passe en parametre\n\tl'acces a vos fichier (ne doit pas etre prive)\n\tla version de votre jre"),
    UNDEFINED_ERROR("Une erreur imprevue est survenue\n\tVous avez peut etre voulu analyser les elements d'un dossier\n\tsans preciser le type de fichier <-t> odt, <-s> ods ou <-p> odp\n\tOu vous n'avez tout simplement pas de chance...\nVoir la documentation\n<?> java -jar odfanalyser.jar");

    // attributs
    private String description;

    // méthodes
    ExceptionTypes(String str)
    {
        description = str;
    }

    public String getDescription()
    {
        return description;
    }
}
