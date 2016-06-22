package systems.rcd.fwk.core.log.data;

public class RcdLogTheme
{
    private final String theme;

    public RcdLogTheme( final String theme )
    {
        this.theme = theme;
    }

    @Override
    public int hashCode()
    {
        return theme.hashCode();
    }

    @Override
    public boolean equals( final Object obj )
    {
        if ( obj instanceof RcdLogTheme )
        {
            return theme.equals( ( (RcdLogTheme) obj ).theme );
        }
        return false;
    }

    @Override
    public String toString()
    {
        return theme;
    }
}
