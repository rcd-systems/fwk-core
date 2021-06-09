package systems.rcd.fwk.core.script.js.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.script.js.RcdJavascriptService;

public class RcdSimpleJavascriptService
    implements RcdJavascriptService
{
    private final ScriptEngine scriptEngine;

    public RcdSimpleJavascriptService()
    {
        this.scriptEngine = new NashornScriptEngineFactory().getScriptEngine();
    }

    @Override
    public Object instEval( final String script )
    {
        try
        {
            return scriptEngine.eval( script );
        }
        catch ( ScriptException e )
        {
            throw new RcdException( "Error while evaluating script", e );
        }
    }
}
