package systems.rcd.fwk.core.log;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdLogService extends RcdService {
    public static void debug(final String theme, final Object... args) {
        log(RcdLogLevel.DEBUG, theme, args);
    }

    public static void info(final String theme, final Object... args) {
        log(RcdLogLevel.INFO, theme, args);
    }

    public static void warn(final String theme, final Object... args) {
        log(RcdLogLevel.WARN, theme, args);
    }

    public static void error(final String theme, final Object... args) {
        log(RcdLogLevel.ERROR, theme, args);
    }

    public static void fatal(final String theme, final Object... args) {
        log(RcdLogLevel.FATAL, theme, args);
    }

    public static void log(final RcdLogLevel logLevel, final String theme, final Object... args) {
        RcdContext.getService(RcdLogService.class).instLog(logLevel, theme, args);
    }

    public void instLog(final RcdLogLevel logLevel, final String theme, final Object... args);
}
