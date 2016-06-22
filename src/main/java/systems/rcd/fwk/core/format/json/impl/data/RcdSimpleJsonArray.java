package systems.rcd.fwk.core.format.json.impl.data;

import java.util.LinkedList;

import systems.rcd.fwk.core.format.json.data.RcdJsonArray;
import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
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
}
