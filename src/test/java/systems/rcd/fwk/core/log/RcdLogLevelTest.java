package systems.rcd.fwk.core.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.log.data.RcdLogTheme;
import systems.rcd.fwk.core.log.impl.RcdPrintStreamLogService;

public class RcdLogLevelTest
{

    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void before()
    {
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testRcdConsoleLogService()
    {
        final RcdLogTheme theme = new RcdLogTheme( "Theme" );

        final PrintStream printStream = new PrintStream( byteArrayOutputStream );
        ( (RcdPrintStreamLogService) RcdContext.getService( RcdLogService.class ) ).setPrintStream( printStream );

        RcdLogService.debug( theme, "A message" );
        checkOutput( "" );

        RcdLogService.info( theme, "A message" );
        checkOutput( "[INFO]  - Theme - A message" + System.lineSeparator() );

        RcdLogService.warn( theme, "A message" );
        checkOutput( "[WARN]  - Theme - A message" + System.lineSeparator() );

        RcdLogService.error( "A message" );
        checkOutput( "[ERROR] - A message" + System.lineSeparator() );

        RcdLogService.fatal( theme, "A message", new NullPointerException() );
        byteArrayOutputStream.reset();

        ( (RcdPrintStreamLogService) RcdContext.getService( RcdLogService.class ) ).setLevelThreshold( theme,
                                                                                                       RcdLogLevel.DEBUG ).setLogLogLevel(
            false ).setLogTheme( false );

        RcdLogService.debug( theme, "A message" );
        checkOutput( "A message" + System.lineSeparator() );
        RcdLogService.info( theme, "A message" );
        checkOutput( "A message" + System.lineSeparator() );
        RcdLogService.warn( "A message" );
        checkOutput( "A message" + System.lineSeparator() );
        RcdLogService.error( theme, "A message" );
        checkOutput( "A message" + System.lineSeparator() );
        RcdLogService.fatal( theme, "A message", new NullPointerException() );
        byteArrayOutputStream.reset();

    }

    private void checkOutput( final String expectedOutput )
    {
        Assert.assertEquals( expectedOutput, byteArrayOutputStream.toString() );
        byteArrayOutputStream.reset();
    }
}
