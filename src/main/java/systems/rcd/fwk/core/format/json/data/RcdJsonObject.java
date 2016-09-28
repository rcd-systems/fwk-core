package systems.rcd.fwk.core.format.json.data;

import java.util.Set;

public interface RcdJsonObject
    extends RcdJsonValue
{
    @Override
    default RcdJsonValueType getType()
    {
        return RcdJsonValueType.OBJECT;
    }

    RcdJsonObject put( String key, Boolean value );

    RcdJsonObject put( String key, Number value );

    RcdJsonObject put( String key, String value );

    RcdJsonObject put( String key, RcdJsonValue value );

    RcdJsonObject createObject( String key );

    RcdJsonArray createArray( String key );

    Set<String> getKeys();

    RcdJsonValue get( final String key );
}
