package systems.rcd.fwk.core.log.impl;

import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import systems.rcd.fwk.core.log.RcdLogLevel;
import systems.rcd.fwk.core.log.RcdLogService;

public class RcdPrintSteamLogService implements RcdLogService {

    private static final RcdLogLevel DEFAULT_LOG_LEVEL_THRESHOLD = RcdLogLevel.INFO;

    private PrintStream printStream = System.out;
    private final Map<String, RcdLogLevel> logLevelThresholdMap = new ConcurrentHashMap<String, RcdLogLevel>();
    private boolean logLogLevel = true;
    private boolean logTheme = true;

    @Override
    public void instLog(final RcdLogLevel logLevel, final String theme, final Object... args) {
        if (logLevel.ordinal() >= getLogLevelThreshold(theme).ordinal()) {
            for (final Object arg : args) {

                if (logLogLevel) {
                    printLogLevel(logLevel);
                }

                if (logTheme) {
                    printStream.print(theme + " - ");
                }

                if (arg instanceof Throwable) {
                    ((Throwable) arg).printStackTrace(printStream);
                } else {
                    printStream.println(arg.toString());
                }
            }
        }
    }

    private RcdLogLevel getLogLevelThreshold(final String theme) {
        if (theme != null) {
            final RcdLogLevel logLevelThreshold = logLevelThresholdMap.get(theme);
            if (logLevelThreshold != null) {
                return logLevelThreshold;
            }
        }
        return DEFAULT_LOG_LEVEL_THRESHOLD;
    }

    private void printLogLevel(final RcdLogLevel logLevel) {
        final String logLevelString = logLevel.name();
        final StringBuffer sb = new StringBuffer("[").append(logLevelString).append("] ");
        for (int i = logLevelString.length(); i < 5; i++) {
            sb.append(' ');
        }
        sb.append("- ");
        printStream.print(sb.toString());
    }

    public void setPrintStream(final PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setLevelThreshold(final String theme, final RcdLogLevel logLevelThreshold) {
        logLevelThresholdMap.put(theme, logLevelThreshold);
    }

    public void setLogLogLevel(final boolean logLogLevel) {
        this.logLogLevel = logLogLevel;
    }

    public void setLogTheme(final boolean logTheme) {
        this.logTheme = logTheme;
    }

}
