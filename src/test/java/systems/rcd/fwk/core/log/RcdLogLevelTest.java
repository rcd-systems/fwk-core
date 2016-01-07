package systems.rcd.fwk.core.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.Test;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.log.impl.RcdPrintSteamLogService;

public class RcdLogLevelTest {

    @Test
    public void testRcdConsoleLogService() {

        final RcdPrintSteamLogService consoleLogService = (RcdPrintSteamLogService) RcdContext
                .getService(RcdLogService.class);

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        consoleLogService.setPrintStream(printStream);

        consoleLogService.instLog(RcdLogLevel.DEBUG, "Theme", "A message");
        Assert.assertEquals("", byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();

        consoleLogService.instLog(RcdLogLevel.INFO, "Theme", "A message");
        Assert.assertEquals("[INFO]  - Theme - A message" + System.lineSeparator(), byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();

        consoleLogService.instLog(RcdLogLevel.WARN, "Theme", "A message");
        Assert.assertEquals("[WARN]  - Theme - A message" + System.lineSeparator(), byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();

        consoleLogService.instLog(RcdLogLevel.ERROR, "Theme", "A message");
        Assert.assertEquals("[ERROR] - Theme - A message" + System.lineSeparator(), byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();

        consoleLogService.instLog(RcdLogLevel.FATAL, "Theme", "A message", new NullPointerException());
        // TODO
        byteArrayOutputStream.reset();

        consoleLogService.setLevelThreshold("Theme", RcdLogLevel.DEBUG);
        consoleLogService.setLogLogLevel(false);
        consoleLogService.setLogTheme(false);

        consoleLogService.instLog(RcdLogLevel.DEBUG, "Theme", "A message");
        Assert.assertEquals("A message" + System.lineSeparator(), byteArrayOutputStream.toString());
        byteArrayOutputStream.reset();
        consoleLogService.instLog(RcdLogLevel.INFO, "Theme", "A message");
        consoleLogService.instLog(RcdLogLevel.WARN, "Theme", "A message");
        consoleLogService.instLog(RcdLogLevel.ERROR, "Theme", "A message");
        consoleLogService.instLog(RcdLogLevel.FATAL, "Theme", "A message", new NullPointerException());

    }
}
