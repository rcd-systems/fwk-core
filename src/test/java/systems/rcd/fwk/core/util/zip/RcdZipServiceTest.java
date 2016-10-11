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

        final Path zipFilePath = tgtDirectory.toPath().resolve( "src.zip" );
        RcdZipService.zipDirectory( srcDirectory.toPath(), zipFilePath );

        Assert.assertTrue( RcdFileService.getDirectorySize( tgtDirectory.toPath() ) > 0 );
    }
}
