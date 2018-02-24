package systems.rcd.fwk.core.format.json.data;

public interface RcdJsonNumber
    extends RcdJsonValue
{
    @Override
    default RcdJsonValueType getType()
    {
        return RcdJsonValueType.NUMBER;
    }


    Number getValue();
}
