package systems.rcd.fwk.core.io.file;

import java.io.File;
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
        final File lastFile = temporaryFolder.newFile();

        final AtomicInteger nbSubPaths = new AtomicInteger();
        RcdFileService.listSubPaths( temporaryFolder.getRoot().toPath(), p -> nbSubPaths.incrementAndGet() );
        Assert.assertEquals( 3, nbSubPaths.get() );

        RcdTextFileService.write( lastFile.toPath(), "content" );
        final long directorySize = RcdFileService.getDirectorySize( temporaryFolder.getRoot().toPath() );
        Assert.assertEquals( 7l, directorySize );

        RcdFileService.deleteDirectory( lastFile.toPath() );
        nbSubPaths.set( 0 );
        RcdFileService.listSubPaths( temporaryFolder.getRoot().toPath(), p -> nbSubPaths.incrementAndGet() );
        Assert.assertEquals( 2, nbSubPaths.get() );
    }
}
