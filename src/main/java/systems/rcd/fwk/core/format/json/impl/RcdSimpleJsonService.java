package systems.rcd.fwk.core.format.json.impl;

import java.io.IOException;
import java.util.Iterator;

import systems.rcd.fwk.core.format.json.RcdJsonService;
import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonBoolean;
import systems.rcd.fwk.core.format.json.data.RcdJsonNumber;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.format.json.data.RcdJsonString;
import systems.rcd.fwk.core.format.json.data.RcdJsonValue;
import systems.rcd.fwk.core.format.json.impl.data.RcdSimpleJsonArray;
import systems.rcd.fwk.core.format.json.impl.data.RcdSimpleJsonBoolean;
import systems.rcd.fwk.core.format.json.impl.data.RcdSimpleJsonNumber;
import systems.rcd.fwk.core.format.json.impl.data.RcdSimpleJsonObject;
import systems.rcd.fwk.core.format.json.impl.data.RcdSimpleJsonString;

public class RcdSimpleJsonService
    implements RcdJsonService
{
    private static final String NULL_VALUE = "null";

    @Override
    public RcdJsonBoolean instCreateJsonValue( final Boolean value )
    {
        return new RcdSimpleJsonBoolean( value );
    }

    @Override
    public RcdJsonNumber instCreateJsonValue( final Number value )
    {
        return new RcdSimpleJsonNumber( value );
    }

    @Override
    public RcdJsonString instCreateJsonValue( final String value )
    {
        return new RcdSimpleJsonString( value );
    }

    @Override
    public RcdJsonArray instCreateJsonArray()
    {
        return new RcdSimpleJsonArray();
    }

    @Override
    public RcdJsonObject instCreateJsonObject()
    {
        return new RcdSimpleJsonObject();
    }

    @Override
    public void instToString( final RcdJsonValue value, final Appendable output )
        throws IOException
    {
        if ( value == null )
        {
            output.append( NULL_VALUE );
            return;
        }

        switch ( value.getType() )
        {
            case BOOLEAN:
                final Boolean booleanValue = ( (RcdJsonBoolean) value ).getValue();
                output.append( booleanValue == null ? NULL_VALUE : booleanValue.toString() );
                break;
            case NUMBER:
                final Number numberValue = ( (RcdJsonNumber) value ).getValue();
                output.append( numberValue == null ? NULL_VALUE : numberValue.toString() );
                break;
            case STRING:
                final String stringValue = ( (RcdJsonString) value ).getValue();
                if ( stringValue == null )
                {
                    output.append( NULL_VALUE );
                }
                else
                {
                    output.append( "\"" ).
                        append( escapeString( stringValue ) ).
                        append( "\"" );
                }
                break;
            case OBJECT:
                final RcdJsonObject jsonObject = (RcdJsonObject) value;
                output.append( "{" );
                for ( final Iterator<String> iterator = jsonObject.getKeys().iterator(); iterator.hasNext(); )
                {
                    final String key = iterator.next();
                    output.append( "\"" ).append( key ).append( "\"" ).append( ":" );
                    instToString( jsonObject.get( key ), output );
                    if ( iterator.hasNext() )
                    {
                        output.append( ',' );
                    }
                }
                output.append( "}" );
                break;
            case ARRAY:
                final RcdJsonArray jsonArray = (RcdJsonArray) value;
                output.append( "[" );
                for ( final Iterator<RcdJsonValue> iterator = jsonArray.iterator(); iterator.hasNext(); )
                {
                    final RcdJsonValue jsonValue = iterator.next();
                    instToString( jsonValue, output );
                    if ( iterator.hasNext() )
                    {
                        output.append( "," ); //TODO Add pretty print configuration to this service
                    }
                }
                output.append( "]" );
                break;
        }
    }

    private String escapeString( String string )
    {
        return string.replaceAll( "\b", "\\\\b" ).
            replaceAll( "\f", "\\\\f" ).
            replaceAll( "\n", "\\\\n" ).
            replaceAll( "\r", "\\\\r" ).
            replaceAll( "\t", "\\\\t" ).
            replaceAll( "\"", "\\\\\"" ).
            replaceAll( "\\{2}", "\\\\" ).
            replaceAll( "/", "\\/" );
    }
}
