package systems.rcd.fwk.core.format.xls;

import java.nio.file.Path;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;
import systems.rcd.fwk.core.format.xls.data.RcdXlsWorkbook;

public interface RcdXlsService extends RcdService {

    static RcdXlsWorkbook read(final Path path) throws Exception {
        return RcdContext.getService(RcdXlsService.class).instRead(path);
    }

    RcdXlsWorkbook instRead(Path path) throws Exception;

}
