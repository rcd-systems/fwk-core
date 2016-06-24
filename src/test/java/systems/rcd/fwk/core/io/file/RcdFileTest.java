package systems.rcd.fwk.core.io.file;

import java.util.concurrent.atomic.AtomicInteger;

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

        final AtomicInteger nbSubPaths = new AtomicInteger();
        RcdFileService.listSubPaths( temporaryFolder.getRoot().toPath(), p -> nbSubPaths.incrementAndGet() );

        Assert.assertEquals( 3, nbSubPaths.get() );
    }
}
