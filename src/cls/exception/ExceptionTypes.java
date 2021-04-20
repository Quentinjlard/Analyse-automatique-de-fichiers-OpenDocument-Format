package cls.exception;

/**
 * Enum&eacute;ration des types d'exceptions possible pour OdfException
 * @see OdfException exception personnalis&eacute;e
 * @author Corentin Machet
 * @version 1.0
 */
public enum ExceptionTypes
{
    // enumération

    /**
     * Arguments en ligne de commande invalides
     */
    TYPE_ARGUMENT("Le ou les arguments passes en parametre sont invalides\n\t-t pour les fichiers odt\n\t-s pour les fichiers ods\n\t-p pour les fichiers ods\n\t[PATH] sans parametre sinon\nVoir la documentation\n<?> java odfanalyser"),

    /**
     * Chemin pass&eacute; en param&egrave;tre n'existe pas ou est &eacute;ron&eacute;
     */
    INVALID_PATH("Le path vers les fichiers odf est absent ou mal renseigne\n\tCela peut etre du aux arguments <-t>, <-s> ou <-p>\n\tA un mauvais chemin\n\tOu a des caracteres speciaux (dans ce cas, utiliser les guillets)\nVoir la documentation\n<?> java odfanalyser"),

    /**
     * Une ou plusieurs options sont invalides
     */
    INVALID_OPTION("Les options de l'execution sont invalides\n\t-v verbose\n\t-x [EXTRACT PATH] extraction\tle path est facultatif\n\tou -vx [EXTRACT PATH]\nVoir la documentation\n<?> java odfanalyser"),

    /**
     * Le fichier dans la destination est introuvable
     */
    FILE_DOES_NOT_EXIST("Vous essayez d'executer odfanalyser sur un fichier qui n'existe pas\nou est inaccessible"),

    /**
     * Aucun chemin n'est renseign&eacute;
     */
    NEEDED_PATH("L'un des arguments <-t>, <-s> ou <-p> n'est pas suivi d'un chemin direct\nvers les fichiers a analyser\nVoir la documentation\n<?> java odfanalyser"),

    /**
     * L'extension du ou des fichier(s) est invalide ou n'existe pas
     */
    EXTENSION("L'extension renseignee est invalide ou indefinie\nElle ne fait pas partie de la suite OpenDocument Format\nou n'a pas ete specifie\n\nVoir la documentation\n<?> java odfanalyser"),

    /**
     * La conversion des donn&eacute;es &agrave; analyser a &eacute;chou&eacute;
     */
    ODFFILE_FAILED("L'extraction ou la redirection vers le menu approprie a echoue\n\tCela peut etre du a une exception de flux\nVerifiez en particulier :\n\tle chemin passe en parametre\n\tl'acces a vos fichier (ne doit pas etre prive)\n\tla version de votre jre"),

    /**
     * Autre erreur
     */
    UNDEFINED_ERROR("Une erreur imprevue est survenue\n\tVous avez peut etre voulu analyser les elements d'un dossier\n\tsans preciser le type de fichier <-t> odt, <-s> ods ou <-p> odp\n\tOu vous n'avez tout simplement pas de chance...\nVoir la documentation\n<?> java odfanalyser");

    // attributs

    /**
     * Description assici&eacute;e
     * @see getDescription() getter description
     */
    private String description;

    // méthodes

    /**
     * Constructeur
     * @param str Description du type d'exception
     */
    ExceptionTypes(String str)
    {
        description = str;
    }

    /**
     * Getter description
     * @return la description associ&eacute;e
     */
    public String getDescription()
    {
        return description;
    }
}
