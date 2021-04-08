package cls.exception;

public enum ExceptionTypes
{
    // enumération
    TYPE_ARGUMENT(""),
    INVALID_PATH(""),
    INVALID_OPTION(""),
    FILE_DOES_NOT_EXIST(""),
    NEEDED_PATH(""),
    EXTENSION(""),
    UNDEFINED_ERROR("");

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
