package systems.rcd.fwk.core.util.zip;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import systems.rcd.fwk.core.io.file.RcdFileService;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;
import systems.rcd.fwk.core.io.file.params.RcdWriteTextFileParams;

public class RcdZipServiceTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void test()
        throws Exception
    {
        final File srcDirectory = temporaryFolder.newFolder( "src" );
        final File tgtDirectory = temporaryFolder.newFolder( "tgt" );
        final File srcBisDirectory = temporaryFolder.newFolder( "src-bis" );

        temporaryFolder.newFolder( "src", "folder1" );
        final File file1 = temporaryFolder.newFile( "src/folder1/file1.txt" );
        final File file2 = temporaryFolder.newFile( "src/file2.txt" );
        RcdTextFileService.write( RcdWriteTextFileParams.from( file1.toPath(), "content" ) );
        RcdTextFileService.write( RcdWriteTextFileParams.from( file2.toPath(), "content2" ) );

        //Tests zip and unzip
        final Path zipPath = tgtDirectory.toPath().resolve( "srcAndFile2.zip" );
        RcdZipService.zip( zipPath, srcDirectory.toPath(), file2.toPath() );
        Assert.assertTrue( RcdFileService.getSize( zipPath ) > 0 );
        RcdZipService.unzip( zipPath, srcBisDirectory.toPath() );

        Assert.assertEquals( 23l, RcdFileService.getSize( srcBisDirectory.toPath() ) );
        final Path unzippedFile1Path = srcBisDirectory.toPath().resolve( "src/folder1/file1.txt" );
        RcdTextFileService.read( RcdReadTextFileParams.newBuilder().
            path( unzippedFile1Path ).
            contentConsumer( content -> Assert.assertEquals( "content", content ) ).
            build() );
    }

    @Test
    public void testUnzip()
        throws IOException, URISyntaxException
    {
        final File tgtDirectory = temporaryFolder.newFolder( "rcd" );
        final Path path = Paths.get( getClass().getResource( "rcd.zip" ).toURI() );
        RcdZipService.unzip( path, tgtDirectory.toPath() );
    }

    @Test
    public void testUnzipMacOs()
        throws IOException, URISyntaxException
    {
        final File tgtDirectory = temporaryFolder.newFolder( "macos" );
        final Path zipFile = Paths.get( getClass().getResource( "macos.zip" ).toURI() );
        RcdZipService.unzip( zipFile, tgtDirectory.toPath() );

        final AtomicInteger counter = new AtomicInteger();
        RcdFileService.listSubPaths( tgtDirectory.toPath(), path -> counter.incrementAndGet() );
        Assert.assertEquals( 2, counter.get() );
    }

    @Test
    public void testUnzipMacOsFiltered()
        throws IOException, URISyntaxException
    {
        final File tgtDirectory = temporaryFolder.newFolder( "macos-filtered" );
        final Path zipPath = Paths.get( getClass().getResource( "macos.zip" ).toURI() );
        Predicate<ZipEntry> filter = zipEntry -> !zipEntry.getName().startsWith( "__MACOSX/" );
        RcdZipService.unzip( zipPath, tgtDirectory.toPath(), filter );

        final AtomicInteger counter = new AtomicInteger();
        RcdFileService.listSubPaths( tgtDirectory.toPath(), path -> counter.incrementAndGet() );
        Assert.assertEquals( 1, counter.get() );
    }
}


