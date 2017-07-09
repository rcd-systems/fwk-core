package systems.rcd.fwk.core.script.js.impl;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.script.js.RcdJavascriptService;

public class RcdSimpleJavascriptService
    implements RcdJavascriptService
{
    @Override
    public Object instEval( final String script )
    {
        try
        {
            return new ScriptEngineManager().
                getEngineByName( "nashorn" ).
                eval( script );
        }
        catch ( ScriptException e )
        {
            throw new RcdException( "Error while evaluating script", e );
        }
    }
}
