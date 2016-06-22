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
            output.append( "null" );
            return;
        }

        switch ( value.getType() )
        {
            case BOOLEAN:
                final Boolean booleanValue = ( (RcdJsonBoolean) value ).getValue();
                output.append( booleanValue.toString() );
                break;
            case NUMBER:
                final Number numberValue = ( (RcdJsonNumber) value ).getValue();
                output.append( numberValue.toString() );
                break;
            case STRING:
                output.append( "\"" ).append( ( (RcdJsonString) value ).getValue() ).append( "\"" );
                break;
            case OBJECT:
                final RcdJsonObject jsonObject = (RcdJsonObject) value;
                output.append( "{" );
                for ( final Iterator<java.util.Map.Entry<String, RcdJsonValue>> iterator = jsonObject.entrySet().iterator();
                      iterator.hasNext(); )
                {
                    final java.util.Map.Entry<String, RcdJsonValue> jsonData = iterator.next();
                    output.append( "\"" ).append( jsonData.getKey() ).append( "\"" ).append( ":" );
                    instToString( jsonData.getValue(), output );
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
                        output.append( ",\n" ); //TODO Add pretty print configuration to this service
                    }
                }
                output.append( "]" );
                break;
        }
    }
}
