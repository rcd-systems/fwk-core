package systems.rcd.fwk.core.format.json.impl.data;

import java.util.LinkedHashMap;

import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.format.json.data.RcdJsonValue;

public class RcdSimpleJsonObject
    extends LinkedHashMap<String, RcdJsonValue>
    implements RcdJsonObject
{

    @Override
    public RcdJsonObject put( final String key, final Boolean value )
    {
        put( key, new RcdSimpleJsonBoolean( value ) );
        return this;
    }

    @Override
    public RcdJsonObject put( final String key, final Number value )
    {
        put( key, new RcdSimpleJsonNumber( value ) );
        return this;
    }

    @Override
    public RcdJsonObject put( final String key, final String value )
    {
        put( key, new RcdSimpleJsonString( value ) );
        return this;
    }

    @Override
    public RcdJsonObject createObject( final String key )
    {
        final RcdSimpleJsonObject jsonObject = new RcdSimpleJsonObject();
        put( key, jsonObject );
        return this;
    }

    @Override
    public RcdJsonArray createArray( final String key )
    {
        final RcdSimpleJsonArray jsonArray = new RcdSimpleJsonArray();
        put( key, jsonArray );
        return jsonArray;
    }
}
