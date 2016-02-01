package systems.rcd.fwk.core.format.csv.impl;

import java.nio.file.Path;
import java.util.Arrays;

import systems.rcd.fwk.core.format.csv.RcdCsvService;
import systems.rcd.fwk.core.format.csv.data.RcdCsvDocument;
import systems.rcd.fwk.core.format.csv.impl.data.RcdSimpleCsvDocument;
import systems.rcd.fwk.core.format.csv.impl.data.RcdSimpleCsvRow;
import systems.rcd.fwk.core.io.file.RcdFileService;

public class RcdSimpleCsvService implements RcdCsvService {

    private String separator = ",";

    @Override
    public RcdCsvDocument instRead(final Path path) throws Exception {
        final RcdCsvDocument csvDocument = new RcdSimpleCsvDocument();
        RcdFileService.readAsStream(path, lines -> {
            lines.map(line -> new RcdSimpleCsvRow(Arrays.asList(line.split(separator))))
                    .forEach(csvDocument::add);
        });
        return csvDocument;
    }

    public RcdSimpleCsvService setSeparator(final String separator) {
        this.separator = separator;
        return this;
    }

}
