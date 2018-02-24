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
}
