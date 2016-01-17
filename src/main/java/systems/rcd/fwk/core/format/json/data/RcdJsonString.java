package systems.rcd.fwk.core.format.json.data;

public interface RcdJsonString extends RcdJsonValue {
    @Override
    default RcdJsonValueType getType() {
        return RcdJsonValueType.STRING;
    };

    String getValue();
}
