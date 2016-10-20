package systems.rcd.fwk.core.util.zip;

import java.nio.file.Path;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdZipService
    extends RcdService
{
    static void zipDirectory( final Path target, final Path... sources )
    {
        RcdContext.getService( RcdZipService.class ).instZipDirectory( target, sources );

    }

    static void unzipDirectory( final Path source, final Path target )
    {
        RcdContext.getService( RcdZipService.class ).instUnzipDirectory( source, target );

    }

    void instZipDirectory( final Path directory, final Path... sources );

    void instUnzipDirectory( final Path source, final Path target );
}
   
