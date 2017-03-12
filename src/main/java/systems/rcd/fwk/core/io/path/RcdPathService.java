package systems.rcd.fwk.core.io.path;

import java.nio.file.Path;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdPathService
    extends RcdService
{
    static String toUnixPathString( final Path path )
    {
        return RcdContext.getService( RcdPathService.class ).instToUnixPathString( path );
    }

    String instToUnixPathString( final Path directory );
}
