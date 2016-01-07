package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdFileService extends RcdService {

    public static String readAsString(final Path path) throws Exception {
        return RcdContext.getService(RcdFileService.class).instReadAsString(path);
    }

    public static void readAsStream(final Path path, final Consumer<Stream<String>> reader) throws Exception {
        RcdContext.getService(RcdFileService.class).instReadAsStream(path, reader);
    }

    public String instReadAsString(final Path path) throws Exception;

    public void instReadAsStream(final Path path, final Consumer<Stream<String>> consumer) throws Exception;

}
