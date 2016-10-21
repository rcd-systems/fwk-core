package systems.rcd.fwk.core.util.zip.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.util.zip.RcdZipService;

public class RcdSimpleZipService
    implements RcdZipService
{
    static final int BUFFER_SIZE = 2048;

    @Override
    public void instZip( final Path target, final Path... sources )
    {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream( new BufferedOutputStream( new FileOutputStream( target.toFile() ) ) ))
        {
            byte buffer[] = new byte[BUFFER_SIZE];

            for ( Path source : sources )
            {
                Files.walkFileTree( source, new SimpleFileVisitor<Path>()
                {
                    @Override
                    public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
                        throws IOException
                    {
                        final Path relativeFilePath = source.getFileName().resolve( source.relativize( file ) );
                        final ZipEntry zipEntry = new ZipEntry( relativeFilePath.toString() );
                        zipOutputStream.putNextEntry( zipEntry );

                        try (InputStream inputStream = new BufferedInputStream( new FileInputStream( file.toFile() ), BUFFER_SIZE ))
                        {
                            int bytesCount;
                            while ( ( bytesCount = inputStream.read( buffer, 0, BUFFER_SIZE ) ) != -1 )
                            {
                                zipOutputStream.write( buffer, 0, bytesCount );
                            }
                        }

                        return FileVisitResult.CONTINUE;
                    }
                } );
            }

        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while zipping files", e );
        }
    }

    @Override
    public void instUnzip( final Path source, final Path target )
    {
        try (ZipInputStream zipInputStream = new ZipInputStream( new BufferedInputStream( new FileInputStream( source.toFile() ) ) ))
        {
            byte buffer[] = new byte[BUFFER_SIZE];

            ZipEntry entry;
            while ( ( entry = zipInputStream.getNextEntry() ) != null )
            {
                final File targetFile = target.resolve( entry.getName() ).toFile();
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();

                try (OutputStream outputStream = new BufferedOutputStream( new FileOutputStream( targetFile ), BUFFER_SIZE );)
                {
                    int bytesCount;
                    while ( ( bytesCount = zipInputStream.read( buffer, 0, BUFFER_SIZE ) ) != -1 )
                    {
                        outputStream.write( buffer, 0, bytesCount );
                    }
                }
            }
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while unzipping files", e );
        }
    }
}
