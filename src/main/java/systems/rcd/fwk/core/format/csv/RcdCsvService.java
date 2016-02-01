package systems.rcd.fwk.core.format.csv;

import java.nio.file.Path;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.format.csv.data.RcdCsvDocument;

public interface RcdCsvService extends RcdService {
    static RcdCsvDocument read(final Path path) throws Exception {
        return RcdContext.getService(RcdCsvService.class)
                .instRead(path);
    }

    RcdCsvDocument instRead(Path path) throws Exception;
}
