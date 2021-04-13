package cls.exception;

public class OdfException extends Exception
{
    private static final long serialVersionUID = 1L;
    private ExceptionTypes type;

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
