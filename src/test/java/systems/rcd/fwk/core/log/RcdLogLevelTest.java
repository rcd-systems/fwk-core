package systems.rcd.fwk.core.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.log.impl.RcdPrintSteamLogService;

public class RcdLogLevelTest {

    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void before() {
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testRcdConsoleLogService() {
        final RcdPrintSteamLogService consoleLogService = (RcdPrintSteamLogService) RcdContext
                .getService(RcdLogService.class);
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        consoleLogService.setPrintStream(printStream);

        consoleLogService.instLog(RcdLogLevel.DEBUG, "Theme", "A message");
        checkOutput("");

        consoleLogService.instLog(RcdLogLevel.INFO, "Theme", "A message");
        checkOutput("[INFO]  - Theme - A message" + System.lineSeparator());

        consoleLogService.instLog(RcdLogLevel.WARN, "Theme", "A message");
        checkOutput("[WARN]  - Theme - A message" + System.lineSeparator());

        consoleLogService.instLog(RcdLogLevel.ERROR, "Theme", "A message");
        checkOutput("[ERROR] - Theme - A message" + System.lineSeparator());

        consoleLogService.instLog(RcdLogLevel.FATAL, "Theme", "A message", new NullPointerException());
        byteArrayOutputStream.reset();

        consoleLogService.setLevelThreshold("Theme", RcdLogLevel.DEBUG);
        consoleLogService.setLogLogLevel(false);
        consoleLogService.setLogTheme(false);

        consoleLogService.instLog(RcdLogLevel.DEBUG, "Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        consoleLogService.instLog(RcdLogLevel.INFO, "Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        consoleLogService.instLog(RcdLogLevel.WARN, "Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        consoleLogService.instLog(RcdLogLevel.ERROR, "Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        consoleLogService.instLog(RcdLogLevel.FATAL, "Theme", "A message", new NullPointerException());
        byteArrayOutputStream.reset();

    }

    private void checkOutput(final String expectedOutput) {
        Assert.assertEquals(expectedOutput, byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();
    }
}
