package systems.rcd.fwk.core.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junit.framework.Assert;

public class RcdFileTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void test()
        throws Exception
    {

        final List<String> lineList = Arrays.asList( new String[]{"Some content", "こんにちわ", "æøå"} );
        final String winContent = "Some content\r\nこんにちわ\r\næøå";
        final String unixContent = "Some content\nこんにちわ\næøå";

        final File temporaryFile1 = temporaryFolder.newFile();
        final File temporaryFile2 = temporaryFolder.newFile();
        final File temporaryFile3 = temporaryFolder.newFile();

        RcdTextFileService.write( temporaryFile1.toPath(), winContent );
        RcdTextFileService.write( temporaryFile2.toPath(), unixContent );
        RcdTextFileService.write( temporaryFile3.toPath(), lineList );

        Assert.assertEquals( winContent, RcdTextFileService.readAsString( temporaryFile1.toPath() ) );
        Assert.assertEquals( unixContent, RcdTextFileService.readAsString( temporaryFile2.toPath() ) );
        RcdTextFileService.readAsStream( temporaryFile3.toPath(), lines -> {
            final List<String> actualList = lines.collect( Collectors.toList() );
            Assert.assertEquals( lineList, actualList );
        } );

    }
}
