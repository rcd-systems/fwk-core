package systems.rcd.fwk.core.format.json.impl.data;

import java.util.LinkedList;

import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonBoolean;
import systems.rcd.fwk.core.format.json.data.RcdJsonNumber;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.format.json.data.RcdJsonString;
import systems.rcd.fwk.core.format.json.data.RcdJsonValue;

public class RcdSimpleJsonArray
    extends LinkedList<RcdJsonValue>
    implements RcdJsonArray
{

    @Override
    public RcdJsonArray add( final Boolean element )
    {
        add( new RcdSimpleJsonBoolean( element ) );
        return this;
    }

    @Override
    public RcdJsonArray add( final Number element )
    {
        add( new RcdSimpleJsonNumber( element ) );
        return this;
    }

    @Override
    public RcdJsonArray add( final String element )
    {
        add( new RcdSimpleJsonString( element ) );
        return this;
    }

    @Override
    public RcdJsonObject createObject()
    {
        final RcdSimpleJsonObject jsonObject = new RcdSimpleJsonObject();
        add( jsonObject );
        return jsonObject;
    }

    @Override
    public RcdJsonArray createArray()
    {
        final RcdSimpleJsonArray jsonArray = new RcdSimpleJsonArray();
        add( jsonArray );
        return jsonArray;
    }

    @Override
    public Boolean getBoolean( final int index )
    {
        final RcdJsonValue value = get( index );
        if ( value instanceof RcdJsonBoolean )
        {
            return ( (RcdJsonBoolean) value ).getValue();
        }
        return null;
    }

    @Override
    public Number getNumber( final int index )
    {
        final RcdJsonValue value = get( index );
        if ( value instanceof RcdJsonNumber )
        {
            return ( (RcdJsonNumber) value ).getValue();
        }
        return null;
    }

    @Override
    public String getString( final int index )
    {
        final RcdJsonValue value = get( index );
        if ( value instanceof RcdJsonString )
        {
            return ( (RcdJsonString) value ).getValue();
        }
        return null;
    }

    @Override
    public RcdJsonArray getArray( final int index )
    {
        final RcdJsonValue value = get( index );
        if ( value instanceof RcdJsonArray )
        {
            return ( (RcdJsonArray) value );
        }
        return null;
    }

    @Override
    public RcdJsonObject getObject( final int index )
    {
        final RcdJsonValue value = get( index );
        if ( value instanceof RcdJsonArray )
        {
            return ( (RcdJsonObject) value );
        }
        return null;
    }
}
