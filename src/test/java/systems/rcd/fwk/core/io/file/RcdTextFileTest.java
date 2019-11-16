package systems.rcd.fwk.core.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;
import systems.rcd.fwk.core.io.file.params.RcdWriteTextFileParams;

public class RcdTextFileTest
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

        RcdTextFileService.write( RcdWriteTextFileParams.newBuilder().
            path( temporaryFile1.toPath() ).
            content( winContent ).
            build() );
        RcdTextFileService.write( RcdWriteTextFileParams.newBuilder().
            path( temporaryFile2.toPath() ).
            content( unixContent ).
            build() );
        RcdTextFileService.write( RcdWriteTextFileParams.newBuilder().
            path( temporaryFile3.toPath() ).
            contentLines( lineList ).
            build() );

        RcdTextFileService.read( RcdReadTextFileParams.newBuilder().
            path( temporaryFile1.toPath() ).
            contentConsumer( content -> Assert.assertEquals( winContent, content ) ).
            build() );
        RcdTextFileService.read( RcdReadTextFileParams.newBuilder().
            path( temporaryFile2.toPath() ).
            contentConsumer( content -> Assert.assertEquals( unixContent, content ) ).
            build() );
        RcdTextFileService.read( RcdReadTextFileParams.newBuilder().
            path( temporaryFile3.toPath() ).
            linesConsumer( lines -> Assert.assertEquals( lineList, lines.collect( Collectors.toList() ) ) ).
            build() );
    }
}
