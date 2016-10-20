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
        temporaryFolder.newFolder( "src", "subFolder" );
        final File tgtDirectory = temporaryFolder.newFolder( "tgt" );
        final File textFile = temporaryFolder.newFile( "src/subFolder/text.txt" );
        RcdTextFileService.write( textFile.toPath(), "mycontent" );
        final File srcBisDirectory = temporaryFolder.newFolder( "srcbis" );

        final Path zipFilePath = tgtDirectory.toPath().resolve( "myzip.zip" );
        RcdZipService.zipDirectory( zipFilePath, srcDirectory.toPath(), textFile.toPath() );

        System.out.println( tgtDirectory.toString() );
        Assert.assertTrue( RcdFileService.getDirectorySize( tgtDirectory.toPath() ) > 0 );

        RcdZipService.unzipDirectory( zipFilePath, srcBisDirectory.toPath() );
        System.out.println( srcBisDirectory.toString() );
        Assert.assertEquals( "mycontent", RcdTextFileService.readAsString( srcBisDirectory.toPath().resolve( "text.txt" ) ) );
    }
}
