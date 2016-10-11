package systems.rcd.fwk.core.util.zip.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.util.zip.RcdZipService;

public class RcdSimpleZipService
    implements RcdZipService
{
    static final int BUFFER_SIZE = 2048;

    @Override
    public void instZipDirectory( final Path directory, final Path target )
    {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream( new BufferedOutputStream( new FileOutputStream( target.toFile() ) ) ))
        {
            byte buffer[] = new byte[BUFFER_SIZE];

            Files.walkFileTree( directory, new SimpleFileVisitor<Path>()
            {
                @Override
                public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
                    throws IOException
                {
                    try (BufferedInputStream inputStream = new BufferedInputStream( new FileInputStream( file.toFile() ), BUFFER_SIZE ))
                    {
                        final ZipEntry zipEntry =
                            new ZipEntry( directory.getFileName().resolve( directory.relativize( file ) ).toString() );
                        zipOutputStream.putNextEntry( zipEntry );
                        int count;
                        while ( ( count = inputStream.read( buffer, 0, BUFFER_SIZE ) ) != -1 )
                        {
                            zipOutputStream.write( buffer, 0, count );
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            } );
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while zipping " + directory.toString(), e );
        }
    }
}
