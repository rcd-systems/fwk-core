package systems.rcd.fwk.core.format.json.data;

import java.util.List;

public interface RcdJsonArray
    extends RcdJsonValue, List<RcdJsonValue>
{
    @Override
    default RcdJsonValueType getType()
    {
        return RcdJsonValueType.ARRAY;
    }

    RcdJsonArray add( Boolean element );

    RcdJsonArray add( Number element );

    RcdJsonArray add( String element );

    RcdJsonObject createObject();

    RcdJsonArray createArray();

    Boolean getBoolean( final int index );

    Number getNumber( final int index );

    String getString( final int index );

    RcdJsonArray getArray( final int index );

    RcdJsonObject getObject( final int index );
}
