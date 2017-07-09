package systems.rcd.fwk.core.script.js;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdJavascriptService
    extends RcdService
{
    static Object eval( final String script )
    {
        return RcdContext.getService( RcdJavascriptService.class ).instEval( script );
    }

    Object instEval( final String script );
}
