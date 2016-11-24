package systems.rcd.fwk.core.util.zip;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdZipService
    extends RcdService
{
    static void zip( final Path target, final Path... sources )
    {
        RcdContext.getService( RcdZipService.class ).instZip( target, sources );
    }

    static void unzip( final Path source, final Path target )
    {
        RcdContext.getService( RcdZipService.class ).instUnzip( source, target, null );
    }

    static void unzip( final Path source, final Path target, final Predicate<ZipEntry> filter )
    {
        RcdContext.getService( RcdZipService.class ).instUnzip( source, target, filter );
    }

    void instZip( final Path directory, final Path... sources );

    void instUnzip( final Path source, final Path target, final Predicate<ZipEntry> filter );
}
   
