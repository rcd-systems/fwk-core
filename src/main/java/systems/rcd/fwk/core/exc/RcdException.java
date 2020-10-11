package systems.rcd.fwk.core.exc;

public class RcdException
    extends RuntimeException
{
    public RcdException( final String message )
    {
        super( message );
    }

    public RcdException( final String message, final Throwable cause )
    {
        super( message, cause );
    }
}
