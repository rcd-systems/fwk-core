package systems.rcd.fwk.core.format.json.data;

import java.util.List;

public interface RcdJsonArray extends RcdJsonValue, List<RcdJsonValue> {
    @Override
    default RcdJsonValueType getType() {
        return RcdJsonValueType.ARRAY;
    };
}
