package systems.rcd.fwk.core.io.file;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RcdFileTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void test()
        throws Exception
    {
        temporaryFolder.newFile();
        temporaryFolder.newFile();
        temporaryFolder.newFile();

        final long numSubPaths = RcdFileService.listSubPaths( temporaryFolder.getRoot().toPath(), Stream::count );
        Assert.assertEquals( 3l, numSubPaths );


    }
}
