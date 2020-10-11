package systems.rcd.fwk.core.log;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.log.data.RcdLogTheme;

public interface RcdLogService
    extends RcdService
{
    static void debug( final Object... args )
    {
        log( RcdLogLevel.DEBUG, null, args );
    }

    static void info( final Object... args )
    {
        log( RcdLogLevel.INFO, null, args );
    }

    static void warn( final Object... args )
    {
        log( RcdLogLevel.WARN, null, args );
    }

    static void error( final Object... args )
    {
        log( RcdLogLevel.ERROR, null, args );
    }

    static void fatal( final Object... args )
    {
        log( RcdLogLevel.FATAL, null, args );
    }

    static void debug( final RcdLogTheme theme, final Object... args )
    {
        log( RcdLogLevel.DEBUG, theme, args );
    }

    static void info( final RcdLogTheme theme, final Object... args )
    {
        log( RcdLogLevel.INFO, theme, args );
    }

    static void warn( final RcdLogTheme theme, final Object... args )
    {
        log( RcdLogLevel.WARN, theme, args );
    }

    static void error( final RcdLogTheme theme, final Object... args )
    {
        log( RcdLogLevel.ERROR, theme, args );
    }

    static void fatal( final RcdLogTheme theme, final Object... args )
    {
        log( RcdLogLevel.FATAL, theme, args );
    }

    static void log( final RcdLogLevel logLevel, final RcdLogTheme theme, final Object... args )
    {
        RcdContext.getService( RcdLogService.class ).instLog( logLevel, theme, args );
    }

    static RcdLogTheme createLogTheme( final String theme )
    {
        return RcdContext.getService( RcdLogService.class ).instCreateLogTheme( theme );
    }

    void instLog( final RcdLogLevel logLevel, final RcdLogTheme theme, final Object... args );

    RcdLogTheme instCreateLogTheme( String theme );
}
