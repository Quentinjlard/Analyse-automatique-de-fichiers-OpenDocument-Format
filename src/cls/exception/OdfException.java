package cls.exception;


/**
 * Gestion des exceptions personnalis&eacute;es de l'application OdfAnalyser
 * @author Corentin Machet
 * @version 1.0
 */
public class OdfException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Type de l'exception 
     * @see ExceptionTypes types d'exception
     */
    private ExceptionTypes type;

    /**
     * Constructeur
     * @param ex type d'exception
     */
    public OdfException(ExceptionTypes ex)
    {
        super(ex.getDescription());
        type = ex;
    }

    @Override
    public String getMessage()
    {
        String str = type.toString();
        str += "\n-------------------------\n";
        str += super.getMessage();
        return str;
    }
}
