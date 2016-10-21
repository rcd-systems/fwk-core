package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.util.function.Consumer;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdFileService
    extends RcdService
{
    static void listSubPaths( final Path path, final Consumer<Path> pathConsumer )
    {
        RcdContext.getService( RcdFileService.class ).instListSubPaths( path, pathConsumer );
    }

    static void delete( final Path directory )
    {
        RcdContext.getService( RcdFileService.class ).instDelete( directory );
    }

    static long getSize( final Path directory )
    {
        return RcdContext.getService( RcdFileService.class ).instGetSize( directory );
    }

    void instListSubPaths( final Path path, final Consumer<Path> pathConsumer );
    
    void instDelete( final Path directory );

    long instGetSize( final Path directory );
}
