package systems.rcd.fwk.core.format.json.data;

import java.util.Map;

public interface RcdJsonObject extends RcdJsonValue, Map<String, RcdJsonValue> {
    @Override
    default RcdJsonValueType getType() {
        return RcdJsonValueType.OBJECT;
    };
}
