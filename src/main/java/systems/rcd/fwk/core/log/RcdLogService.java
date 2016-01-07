package systems.rcd.fwk.core.log;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdLogService extends RcdService {
    public static void log(final RcdLogLevel logLevel, final String theme, final Object... args) {
        RcdContext.getService(RcdLogService.class).instLog(logLevel, theme, args);
    }

    public void instLog(final RcdLogLevel logLevel, final String theme, final Object... args);
}
