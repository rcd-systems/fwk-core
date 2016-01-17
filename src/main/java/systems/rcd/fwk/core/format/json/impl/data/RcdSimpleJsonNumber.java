package systems.rcd.fwk.core.format.json.impl.data;

import systems.rcd.fwk.core.format.json.data.RcdJsonNumber;

public class RcdSimpleJsonNumber implements RcdJsonNumber {

    private final Number value;

    public RcdSimpleJsonNumber(final Number value) {
        this.value = value;
    }

    @Override
    public Number getValue() {
        return value;
    }

}
