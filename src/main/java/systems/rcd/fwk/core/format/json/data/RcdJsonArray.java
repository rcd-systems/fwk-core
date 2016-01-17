package systems.rcd.fwk.core.format.json.data;

import java.util.List;

public interface RcdJsonArray extends RcdJsonValue, List<RcdJsonValue> {
    @Override
    default RcdJsonValueType getType() {
        return RcdJsonValueType.ARRAY;
    };

    public RcdJsonArray add(Boolean element);

    public RcdJsonArray add(Number element);

    public RcdJsonArray add(String element);

    public RcdJsonObject createObject();

    public RcdJsonArray createArray();
}
