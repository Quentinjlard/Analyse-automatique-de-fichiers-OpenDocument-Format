package cls.ext;

/**
 * Types d'extension g&eacute;r&eacute;es par OdfAnalyser
 * @author Corentin Machet
 * @version 1.0
 */
public enum Extension
{
    /**
     * Extension par d&eacute;faut. Ind&eacute;finie.
     */
    UNDEFINED(""),

    /**
     * Extension pour le format text (odt)
     */
    ODT(".odt"),

    /**
     * Extension pour le format tableur (ods)
     */
    ODS(".ods"),

    /**
     * Extension pour le format pr&eacute;sentation (odp)
     */
    ODP(".odp");

    /**
     * Descrption associ&eacute;e &agrave; l'extension
     */
    private String ext;

    /**
     * Constructeur
     * @param ext description
     */
    Extension(String ext)
    {
        this.ext = ext;
    }

    @Override
    public String toString()
    {
        return ext;
    }
}
