package systems.rcd.fwk.core.io.file;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import systems.rcd.fwk.core.io.file.params.RcdWriteTextFileParams;

public class RcdFileTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    final AtomicInteger counter = new AtomicInteger();

    @Test
    public void test()
        throws Exception
    {
        final File folder1 = temporaryFolder.newFolder( "folder1" );
        temporaryFolder.newFolder( "folder2" );
        temporaryFolder.newFile( "folder1/file1" );
        final File file2 = temporaryFolder.newFile( "folder1/file2" );
        final File file3 = temporaryFolder.newFile( "folder2/file3" );
        RcdTextFileService.write( RcdWriteTextFileParams.from( file2.toPath(), "content" ) );
        RcdTextFileService.write( RcdWriteTextFileParams.from( file3.toPath(), "content2" ) );

        //Tests listSubPaths
        assertSubPathCount( 2, folder1.toPath() );

        //Tests getSize for a file and directory
        final long directorySize = RcdFileService.getSize( temporaryFolder.getRoot().toPath() );
        final long fileSize = RcdFileService.getSize( file2.toPath() );
        Assert.assertEquals( 7l, fileSize );
        Assert.assertEquals( 15l, directorySize );

        //Tests delete for a file and directory
        assertSubPathCount( 2, folder1.toPath() );
        RcdFileService.delete( file2.toPath() );
        assertSubPathCount( 1, folder1.toPath() );
        assertSubPathCount( 2, temporaryFolder.getRoot().toPath() );
        RcdFileService.delete( folder1.toPath() );
        assertSubPathCount( 1, temporaryFolder.getRoot().toPath() );
    }

    private void assertSubPathCount( final int expectedCount, final Path path )
    {
        counter.set( 0 );
        RcdFileService.listSubPaths( path, p -> counter.incrementAndGet() );
        Assert.assertEquals( expectedCount, counter.get() );
    }
}
