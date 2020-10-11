package systems.rcd.fwk.core.io.file;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;
import systems.rcd.fwk.core.io.file.params.RcdWriteTextFileParams;

public interface RcdTextFileService
    extends RcdService
{
    static void read( final RcdReadTextFileParams params )
    {
        RcdContext.getService( RcdTextFileService.class ).instRead( params );
    }

    static void write( final RcdWriteTextFileParams params )
    {
        RcdContext.getService( RcdTextFileService.class ).instWrite( params );
    }

    void instRead( final RcdReadTextFileParams params );

    void instWrite( final RcdWriteTextFileParams params );

}
