package systems.rcd.fwk.core.format.xls.data;

import java.time.LocalDateTime;

public interface RcdXlsCell
{
    RcdXlsCellType getType();

    String getStringValue();

    LocalDateTime getDateTimeValue();

    Double getNumericValue();

    Boolean getBooleanValue();
}
