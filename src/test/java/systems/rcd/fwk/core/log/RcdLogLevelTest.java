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
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ((RcdPrintSteamLogService) RcdContext.getService(RcdLogService.class)).setPrintStream(printStream);

        RcdLogService.debug("Theme", "A message");
        checkOutput("");

        RcdLogService.info("Theme", "A message");
        checkOutput("[INFO]  - Theme - A message" + System.lineSeparator());

        RcdLogService.warn("Theme", "A message");
        checkOutput("[WARN]  - Theme - A message" + System.lineSeparator());

        RcdLogService.error("Theme", "A message");
        checkOutput("[ERROR] - Theme - A message" + System.lineSeparator());

        RcdLogService.fatal("Theme", "A message", new NullPointerException());
        byteArrayOutputStream.reset();

        ((RcdPrintSteamLogService) RcdContext.getService(RcdLogService.class))
        .setLevelThreshold("Theme", RcdLogLevel.DEBUG).setLogLogLevel(false).setLogTheme(false);

        RcdLogService.debug("Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        RcdLogService.info("Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        RcdLogService.warn("Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        RcdLogService.error("Theme", "A message");
        checkOutput("A message" + System.lineSeparator());
        RcdLogService.fatal("Theme", "A message", new NullPointerException());
        byteArrayOutputStream.reset();

    }

    private void checkOutput(final String expectedOutput) {
        Assert.assertEquals(expectedOutput, byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();
    }
}
