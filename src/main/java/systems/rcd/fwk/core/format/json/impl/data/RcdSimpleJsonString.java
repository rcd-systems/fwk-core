package systems.rcd.fwk.core.format.json.impl.data;

import systems.rcd.fwk.core.format.json.data.RcdJsonString;

public class RcdSimpleJsonString
    implements RcdJsonString
{
    private final String value;

    public RcdSimpleJsonString( final String value )
    {
        this.value = value;
    }

    @Override
    public String getValue()
    {
        return value;
    }

}
