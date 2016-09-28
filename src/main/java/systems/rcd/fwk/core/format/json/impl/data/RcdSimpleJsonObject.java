package systems.rcd.fwk.core.format.json.impl.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
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
    public Set<String> getKeys()
    {
        return internalMap.keySet();
    }

    @Override
    public RcdJsonValue get( final String key )
    {
        return internalMap.get( key );
    }
}
