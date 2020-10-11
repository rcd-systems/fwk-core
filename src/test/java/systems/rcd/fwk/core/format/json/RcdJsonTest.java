package systems.rcd.fwk.core.format.json;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import systems.rcd.fwk.core.format.json.data.RcdJsonObject;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;

public class RcdJsonTest
{

    @Test
    public void test()
        throws Exception
    {
        final RcdJsonObject jsonObject = RcdJsonService.createJsonObject().
            put( "aString", "string value\nsecond\tline:\"quoted/text\"" ).
            put( "aInteger", 42 ).
            put( "aFloat", 3.14 ).
            put( "aBoolean", true ).
            put( "aNullValue", (String) null ).
            put( "anArrayOfString", RcdJsonService.createJsonArray().
                add( "first" ).
                add( "second" ) ).
            put( "subObject", RcdJsonService.createJsonObject().
                put( "subObjectField", "value" ) );

        final Path expectedPath = Paths.get( getClass().getResource( "expected.json" ).toURI() );
        RcdTextFileService.read( RcdReadTextFileParams.newBuilder().
            path( expectedPath ).
            contentConsumer( content -> Assert.assertEquals( content, RcdJsonService.toString( jsonObject ) ) ).
            build() );
    }
}
