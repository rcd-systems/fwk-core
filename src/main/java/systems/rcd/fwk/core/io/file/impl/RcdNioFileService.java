package systems.rcd.fwk.core.io.file.impl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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

    @Override
    public void instDeleteDirectory( final Path directory )
    {
        try
        {
            Files.walkFileTree( directory, new SimpleFileVisitor<Path>()
            {
                @Override
                public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
                    throws IOException
                {
                    Files.delete( file );
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory( Path dir, IOException exc )
                    throws IOException
                {
                    Files.delete( dir );
                    return FileVisitResult.CONTINUE;
                }

            } );
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while deleting directory [" + directory + "]", e );
        }
    }
}