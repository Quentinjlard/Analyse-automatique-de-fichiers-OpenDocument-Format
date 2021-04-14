package cls.ext;

public enum Extension
{
    UNDEFINED(""),
    ODT(".odt"),
    ODS(".ods"),
    ODP(".odp");

    private String ext;

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
