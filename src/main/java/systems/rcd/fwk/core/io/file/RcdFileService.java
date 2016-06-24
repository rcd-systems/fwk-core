package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdFileService
    extends RcdService
{
    static <T> T listSubPaths( final Path path, final Function<Stream<Path>, T> function )
    {
        return RcdContext.getService( RcdFileService.class ).instListSubPaths( path, function );
    }

    <T> T instListSubPaths( Path path, Function<Stream<Path>, T> consumer );
}
