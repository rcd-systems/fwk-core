package systems.rcd.fwk.core.script.js;

import org.junit.Assert;
import org.junit.Test;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class RcdJavascriptTest
{

    @Test
    public void test()
    {
        final Object sum = RcdJavascriptService.eval( "1 + 2" );
        Assert.assertEquals( 3, sum );

        final ScriptObjectMirror parsedJson = (ScriptObjectMirror) RcdJavascriptService.eval( "JSON.parse('{\"property\":\"value\"}')" );
        Assert.assertEquals( "value", parsedJson.get( "property" ) );
    }
}
