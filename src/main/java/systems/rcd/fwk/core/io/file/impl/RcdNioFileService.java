package systems.rcd.fwk.core.io.file.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.io.file.RcdFileService;

public class RcdNioFileService
    implements RcdFileService
{
    public void instListSubPaths( final Path path, final Consumer<Path> pathConsumer )
    {
        try
        {
            try (Stream<Path> subPathStream = Files.list( path ))
            {
                subPathStream.forEach( pathConsumer );
            }
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while listing sub path of [" + path + "]", e );
        }
    }
}
