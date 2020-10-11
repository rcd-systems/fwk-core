package systems.rcd.fwk.core.util.zip;

import systems.rcd.fwk.core.exc.RcdException;

public class Preconditions
{
    public static void checkNotNull( final Object object, final String errorMessage )
    {
        if ( object == null )
        {
            throw new RcdException( errorMessage );
        }
    }

    public static void check( final boolean expression, final String errorMessage )
    {
        if ( !expression )
        {
            throw new RcdException( errorMessage );
        }
    }

    public static void checkNot( final boolean expression, final String errorMessage )
    {
        if ( expression )
        {
            throw new RcdException( errorMessage );
        }
    }
}
