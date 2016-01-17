package systems.rcd.fwk.core.log;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.log.data.RcdLogTheme;

public interface RcdLogService extends RcdService {
    public static void debug(final Object... args) {
        log(RcdLogLevel.DEBUG, null, args);
    }

    public static void info(final Object... args) {
        log(RcdLogLevel.INFO, null, args);
    }

    public static void warn(final Object... args) {
        log(RcdLogLevel.WARN, null, args);
    }

    public static void error(final Object... args) {
        log(RcdLogLevel.ERROR, null, args);
    }

    public static void fatal(final Object... args) {
        log(RcdLogLevel.FATAL, null, args);
    }

    public static void debug(final RcdLogTheme theme, final Object... args) {
        log(RcdLogLevel.DEBUG, theme, args);
    }

    public static void info(final RcdLogTheme theme, final Object... args) {
        log(RcdLogLevel.INFO, theme, args);
    }

    public static void warn(final RcdLogTheme theme, final Object... args) {
        log(RcdLogLevel.WARN, theme, args);
    }

    public static void error(final RcdLogTheme theme, final Object... args) {
        log(RcdLogLevel.ERROR, theme, args);
    }

    public static void fatal(final RcdLogTheme theme, final Object... args) {
        log(RcdLogLevel.FATAL, theme, args);
    }

    public static void log(final RcdLogLevel logLevel, final RcdLogTheme theme, final Object... args) {
        RcdContext.getService(RcdLogService.class)
                .instLog(logLevel, theme, args);
    }

    public void instLog(final RcdLogLevel logLevel, final RcdLogTheme theme, final Object... args);
}
