package systems.rcd.fwk.core.format.properties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class RcdPropertiesTest
{

    @Test
    public void test()
        throws Exception
    {
        final Path inputPath = Paths.get( getClass().getResource( "input.properties" ).toURI() );
        final Map<String, String> properties = RcdPropertiesService.read( inputPath );
        Assert.assertEquals( 4, properties.size() );
        Assert.assertEquals( "b", properties.get( "a" ) );
        Assert.assertEquals( "d", properties.get( "c" ) );
        Assert.assertEquals( "i", properties.get( "ef.gh" ) );
        Assert.assertEquals( "k-l", properties.get( "j" ) );
    }
}
