package systems.rcd.fwk.core.format.json.impl.data;

import systems.rcd.fwk.core.format.json.data.RcdJsonBoolean;

public class RcdSimpleJsonBoolean implements RcdJsonBoolean {
    private final Boolean value;

    public RcdSimpleJsonBoolean(final Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
