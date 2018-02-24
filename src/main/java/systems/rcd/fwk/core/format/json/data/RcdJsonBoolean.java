package systems.rcd.fwk.core.format.json.data;

public interface RcdJsonBoolean
    extends RcdJsonValue
{
    @Override
    default RcdJsonValueType getType()
    {
        return RcdJsonValueType.BOOLEAN;
    }

    Boolean getValue();
}
