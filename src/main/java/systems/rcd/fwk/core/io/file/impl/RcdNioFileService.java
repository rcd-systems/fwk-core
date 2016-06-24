package systems.rcd.fwk.core.io.file.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.io.file.RcdFileService;

public class RcdNioFileService
    implements RcdFileService
{
    public <T> T instListSubPaths( final Path path, final Function<Stream<Path>, T> function )
    {
        try
        {
            try (Stream<Path> subPathStream = Files.list( path ))
            {
                return function.apply( subPathStream );
            }
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while listing sub path of [" + path + "]", e );
        }
    }
}
