package systems.rcd.fwk.core.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class RcdPathTest
{
    @Test
    public void test()
        throws Exception
    {
        final Path path = Paths.get( "a", "b" );
        final String unixPathString = RcdPathService.toUnixPathString( path );
        Assert.assertEquals( "a/b", unixPathString );
    }
}
