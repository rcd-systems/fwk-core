package systems.rcd.fwk.core.util.zip.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

//TODO Rewrite properly
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
                        try (BufferedInputStream inputStream = new BufferedInputStream( new FileInputStream( file.toFile() ), BUFFER_SIZE ))
                        {
                            final ZipEntry zipEntry = new ZipEntry( source.getFileName().resolve( source.relativize( file ) ).toString() );
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

            ZipEntry entry;
            while ( ( entry = zipInputStream.getNextEntry() ) != null )
            {
                int count;
                byte buffer[] = new byte[BUFFER_SIZE];
                final File targetFile = target.resolve( entry.getName() ).toFile();
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
                FileOutputStream fos = new FileOutputStream( targetFile );
                BufferedOutputStream dest = new BufferedOutputStream( fos, BUFFER_SIZE );
                while ( ( count = zipInputStream.read( buffer, 0, BUFFER_SIZE ) ) != -1 )
                {
                    dest.write( buffer, 0, count );
                }
                dest.flush();
                dest.close();
            }
        }
        catch ( IOException e )
        {
            throw new RcdException( "Error while zipping files", e );
        }
    }
}
