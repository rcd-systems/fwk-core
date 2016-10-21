package systems.rcd.fwk.core.util.zip;

import java.io.File;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import systems.rcd.fwk.core.io.file.RcdFileService;
import systems.rcd.fwk.core.io.file.RcdTextFileService;

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
        RcdTextFileService.write( file1.toPath(), "content" );
        RcdTextFileService.write( file2.toPath(), "content2" );

        //Tests zip and unzip
        final Path zipPath = tgtDirectory.toPath().resolve( "srcAndFile2.zip" );
        RcdZipService.zip( zipPath, srcDirectory.toPath(), file2.toPath() );
        Assert.assertTrue( RcdFileService.getSize( zipPath ) > 0 );
        RcdZipService.unzip( zipPath, srcBisDirectory.toPath() );

        Assert.assertEquals( 23l, RcdFileService.getSize( srcBisDirectory.toPath() ) );
        final Path unzipedFile1Path = srcBisDirectory.toPath().resolve( "src/folder1/file1.txt" );
        Assert.assertEquals( "content", RcdTextFileService.readAsString( unzipedFile1Path ) );
    }
}
