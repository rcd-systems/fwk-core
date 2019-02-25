package systems.rcd.fwk.core.helper;

import systems.rcd.fwk.core.exc.RcdException;

public class RcdCheckHelper
{
    public static void checkNotNull( final Object value, final String name )
    {
        if ( value == null )
        {
            throw new RcdException( name + " cannot be null" );
        }
    }
}
