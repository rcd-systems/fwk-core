package systems.rcd.fwk.core.format.xls.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface RcdXlsRow
    extends List<RcdXlsCell>
{
    String getString( int index );

    LocalDateTime getDateTime( int index );

    Number getNumber( int index );

    Boolean getBoolean( int index );

}
