package systems.rcd.fwk.core.format.json;

import java.io.IOException;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonBoolean;
import systems.rcd.fwk.core.format.json.data.RcdJsonNumber;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.format.json.data.RcdJsonString;
import systems.rcd.fwk.core.format.json.data.RcdJsonValue;

public interface RcdJsonService
    extends RcdService
{
    static RcdJsonBoolean createJsonValue( final Boolean value )
    {
        return RcdContext.getService( RcdJsonService.class ).instCreateJsonValue( value );
    }

    static RcdJsonNumber createJsonValue( final Number value )
    {
        return RcdContext.getService( RcdJsonService.class ).instCreateJsonValue( value );
    }

    static RcdJsonString createJsonValue( final String value )
    {
        return RcdContext.getService( RcdJsonService.class ).instCreateJsonValue( value );
    }

    static RcdJsonObject createJsonObject()
    {
        return RcdContext.getService( RcdJsonService.class ).instCreateJsonObject();
    }

    static RcdJsonArray createJsonArray()
    {
        return RcdContext.getService( RcdJsonService.class ).instCreateJsonArray();
    }

    static String toString( final RcdJsonValue value )
    {
        final StringBuilder sb = new StringBuilder();
        try
        {
            RcdContext.getService( RcdJsonService.class ).instToString( value, sb );
        }
        catch ( final IOException e )
        {
        }
        return sb.toString();
    }

    RcdJsonBoolean instCreateJsonValue( Boolean value );

    RcdJsonNumber instCreateJsonValue( Number value );

    RcdJsonString instCreateJsonValue( String value );

    RcdJsonObject instCreateJsonObject();

    RcdJsonArray instCreateJsonArray();

    void instToString( RcdJsonValue value, Appendable output )
        throws IOException;

}
