package systems.rcd.fwk.core.format.csv;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.format.csv.data.RcdCsvDocument;
import systems.rcd.fwk.core.format.csv.params.RcdReadCsvDocumentParams;

public interface RcdCsvService
    extends RcdService
{
    static RcdCsvDocument read( final RcdReadCsvDocumentParams params )
    {
        return RcdContext.getService( RcdCsvService.class ).instRead( params );
    }

    RcdCsvDocument instRead( RcdReadCsvDocumentParams params );
}
