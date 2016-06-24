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

    void instListSubPaths( Path path, final Consumer<Path> pathConsumer );
}
