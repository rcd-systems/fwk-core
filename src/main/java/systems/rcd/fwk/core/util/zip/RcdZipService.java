package systems.rcd.fwk.core.util.zip;

import java.nio.file.Path;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdZipService
    extends RcdService
{
    static void zipDirectory( final Path directory, final Path target )
    {
        RcdContext.getService( RcdZipService.class ).instZipDirectory( directory, target );

    }

    void instZipDirectory( final Path directory, final Path target );
}
   
