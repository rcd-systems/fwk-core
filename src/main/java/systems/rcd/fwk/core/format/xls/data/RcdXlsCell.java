package systems.rcd.fwk.core.format.xls.data;

import java.time.Instant;

public interface RcdXlsCell {
    RcdXlsCellType getType();

    String getStringValue();

    Instant getInstantValue();

    Double getNumericValue();

    Boolean getBooleanValue();
}
