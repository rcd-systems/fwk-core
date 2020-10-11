package systems.rcd.fwk.core.helper;

import java.util.Collection;
import java.util.Map;

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

    public static void checkNotEmpty( final Collection<?> collection, final String name )
    {
        checkNotNull( collection, name );
        if ( collection.isEmpty() )
        {
            throw new RcdException( name + " cannot be empty" );
        }
    }

    public static void checkNotEmpty( final Map<?, ?> collection, final String name )
    {
        checkNotNull( collection, name );
        if ( collection.isEmpty() )
        {
            throw new RcdException( name + " cannot be empty" );
        }
    }
}
