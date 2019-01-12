package systems.rcd.fwk.core.format.json.impl.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonBoolean;
import systems.rcd.fwk.core.format.json.data.RcdJsonNumber;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.format.json.data.RcdJsonString;
import systems.rcd.fwk.core.format.json.data.RcdJsonValue;

public class RcdSimpleJsonObject
    implements RcdJsonObject
{
    private Map<String, RcdJsonValue> internalMap = new LinkedHashMap<>();

    @Override
    public RcdJsonObject put( final String key, final Boolean value )
    {
        internalMap.put( key, new RcdSimpleJsonBoolean( value ) );
        return this;
    }

    @Override
    public RcdJsonObject put( final String key, final Number value )
    {
        internalMap.put( key, new RcdSimpleJsonNumber( value ) );
        return this;
    }

    @Override
    public RcdJsonObject put( final String key, final String value )
    {
        internalMap.put( key, new RcdSimpleJsonString( value ) );
        return this;
    }

    @Override
    public RcdJsonObject put( final String key, final RcdJsonValue value )
    {
        internalMap.put( key, value );
        return this;
    }

    @Override
    public RcdJsonObject createObject( final String key )
    {
        final RcdSimpleJsonObject jsonObject = new RcdSimpleJsonObject();
        put( key, jsonObject );
        return jsonObject;
    }

    @Override
    public RcdJsonArray createArray( final String key )
    {
        final RcdSimpleJsonArray jsonArray = new RcdSimpleJsonArray();
        put( key, jsonArray );
        return jsonArray;
    }

    @Override
    public boolean hasKey( final String key )
    {
        return internalMap.containsKey( key );
    }

    @Override
    public Set<String> getKeys()
    {
        return internalMap.keySet();
    }

    @Override
    public RcdJsonValue get( final String key )
    {
        return internalMap.get( key );
    }

    @Override
    public Boolean getBoolean( final String key )
    {
        final RcdJsonValue value = get( key );
        if ( value instanceof RcdJsonBoolean )
        {
            return ( (RcdJsonBoolean) value ).getValue();
        }
        return null;
    }

    @Override
    public Number getNumber( final String key )
    {
        final RcdJsonValue value = get( key );
        if ( value instanceof RcdJsonNumber )
        {
            return ( (RcdJsonNumber) value ).getValue();
        }
        return null;
    }

    @Override
    public String getString( final String key )
    {
        final RcdJsonValue value = get( key );
        if ( value instanceof RcdJsonString )
        {
            return ( (RcdJsonString) value ).getValue();
        }
        return null;
    }

    @Override
    public RcdJsonArray getArray( final String key )
    {
        final RcdJsonValue value = get( key );
        if ( value instanceof RcdJsonArray )
        {
            return ( (RcdJsonArray) value );
        }
        return null;
    }

    @Override
    public RcdJsonObject getObject( final String key )
    {
        final RcdJsonValue value = get( key );
        if ( value instanceof RcdJsonObject )
        {
            return (RcdJsonObject) value;
        }
        return null;
    }
}
