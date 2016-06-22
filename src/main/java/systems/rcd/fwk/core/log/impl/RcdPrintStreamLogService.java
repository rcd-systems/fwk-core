package systems.rcd.fwk.core.log.impl;

import java.io.PrintStream;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import systems.rcd.fwk.core.log.RcdLogLevel;
import systems.rcd.fwk.core.log.RcdLogService;
import systems.rcd.fwk.core.log.data.RcdLogTheme;

public class RcdPrintStreamLogService
    implements RcdLogService
{

    private static final RcdLogLevel DEFAULT_LOG_LEVEL_THRESHOLD = RcdLogLevel.INFO;

    private PrintStream printStream = System.out;

    private final Map<RcdLogTheme, RcdLogLevel> logLevelThresholdMap = new ConcurrentHashMap<>();

    private RcdLogLevel logLevelThreshold = DEFAULT_LOG_LEVEL_THRESHOLD;

    private final boolean logTime = true;

    private boolean logLogLevel = true;

    private boolean logTheme = true;

    @Override
    public void instLog( final RcdLogLevel logLevel, final RcdLogTheme theme, final Object... args )
    {
        if ( mustLog( logLevel, theme ) )
        {
            for ( final Object arg : args )
            {

                if ( logTime )
                {
                    printTime();
                }

                if ( logLogLevel )
                {
                    printLogLevel( logLevel );
                }

                if ( logTheme && theme != null )
                {
                    printStream.print( theme + " - " );
                }

                if ( arg instanceof Throwable )
                {
                    ( (Throwable) arg ).printStackTrace( printStream );
                }
                else
                {
                    printStream.println( arg.toString() );
                }
            }
        }
    }

    private boolean mustLog( final RcdLogLevel logLevel, final RcdLogTheme theme )
    {
        return logLevel.ordinal() >= getLogLevelThreshold( theme ).ordinal();
    }

    private RcdLogLevel getLogLevelThreshold( final RcdLogTheme theme )
    {
        if ( theme != null )
        {
            final RcdLogLevel logLevelThreshold = logLevelThresholdMap.get( theme );
            if ( logLevelThreshold != null )
            {
                return logLevelThreshold;
            }
        }
        return logLevelThreshold;
    }

    private void printTime()
    {
        printStream.print( Instant.now().toString() + " - " );
    }

    private void printLogLevel( final RcdLogLevel logLevel )
    {
        final String logLevelString = logLevel.name();
        final StringBuffer sb = new StringBuffer( "[" ).append( logLevelString ).append( "] " );
        for ( int i = logLevelString.length(); i < 5; i++ )
        {
            sb.append( ' ' );
        }
        sb.append( "- " );
        printStream.print( sb.toString() );
    }

    public RcdPrintStreamLogService setPrintStream( final PrintStream printStream )
    {
        this.printStream = printStream;
        return this;
    }

    public RcdPrintStreamLogService setLevelThreshold( final RcdLogTheme theme, final RcdLogLevel logLevelThreshold )
    {
        logLevelThresholdMap.put( theme, logLevelThreshold );
        return this;
    }

    public RcdPrintStreamLogService setLogLevelThreshold( final RcdLogLevel logLevelThreshold )
    {
        this.logLevelThreshold = logLevelThreshold;
        return this;
    }

    public RcdPrintStreamLogService setLogLogLevel( final boolean logLogLevel )
    {
        this.logLogLevel = logLogLevel;
        return this;
    }

    public RcdPrintStreamLogService setLogTheme( final boolean logTheme )
    {
        this.logTheme = logTheme;
        return this;
    }

}
