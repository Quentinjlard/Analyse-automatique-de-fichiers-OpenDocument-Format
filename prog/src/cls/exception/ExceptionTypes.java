package cls.exception;

public enum ExceptionTypes
{
    // enumération
    TYPE_ARGUMENT("test : type_argument"),
    INVALID_PATH(""),
    EXTENSION("");

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
