package systems.rcd.fwk.core.format.json.data;

import java.util.Map;

public interface RcdJsonObject extends RcdJsonValue, Map<String, RcdJsonValue> {
    @Override
    default RcdJsonValueType getType() {
        return RcdJsonValueType.OBJECT;
    };

    public RcdJsonObject put(String key, Boolean value);

    public RcdJsonObject put(String key, Number value);

    public RcdJsonObject put(String key, String value);

    public RcdJsonObject createObject(String key);

    public RcdJsonArray createArray(String key);
}
