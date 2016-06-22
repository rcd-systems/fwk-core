package systems.rcd.fwk.core.format.csv.impl.data;

import java.util.ArrayList;
import java.util.List;

import systems.rcd.fwk.core.format.csv.data.RcdCsvRow;

public class RcdSimpleCsvRow
    extends ArrayList<String>
    implements RcdCsvRow
{

    public RcdSimpleCsvRow( final List<String> collection )
    {
        super( collection );
    }

    @Override
    public String get( final int index )
    {
        return size() > index ? super.get( index ) : null;
    }
}
