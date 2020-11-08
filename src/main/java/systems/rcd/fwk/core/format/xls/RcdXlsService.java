package systems.rcd.fwk.core.format.xls;

import java.io.InputStream;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.format.xls.data.RcdXlsWorkbook;

public interface RcdXlsService
    extends RcdService
{
    static RcdXlsWorkbook read( final InputStream inputStream )
    {
        return RcdContext.getService( RcdXlsService.class ).
            instRead( inputStream );
    }

    RcdXlsWorkbook instRead( InputStream inputStream );

}
