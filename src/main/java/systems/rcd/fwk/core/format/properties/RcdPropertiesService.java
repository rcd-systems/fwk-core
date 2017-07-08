package systems.rcd.fwk.core.format.properties;

import java.nio.file.Path;
import java.util.Map;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdPropertiesService
    extends RcdService
{
    static Map<String, String> read( final Path path )
    {
        return RcdContext.getService( RcdPropertiesService.class ).instRead( path );
    }

    Map<String, String> instRead( Path path );
}
