package systems.rcd.fwk.core.format.csv.impl;

import java.util.Arrays;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.format.csv.RcdCsvService;
import systems.rcd.fwk.core.format.csv.impl.data.RcdSimpleCsvDocument;
import systems.rcd.fwk.core.format.csv.impl.data.RcdSimpleCsvRow;
import systems.rcd.fwk.core.format.csv.params.RcdReadCsvDocumentParams;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;

public class RcdSimpleCsvService
    implements RcdCsvService
{
    @Override
    public RcdSimpleCsvDocument instRead( final RcdReadCsvDocumentParams params )
    {
        final RcdSimpleCsvDocument csvDocument = new RcdSimpleCsvDocument();
        try
        {
            final RcdReadTextFileParams readTextFileParams = RcdReadTextFileParams.newBuilder().
                path( params.getPath() ).
                charset( params.getCharset() ).
                linesConsumer( lines -> {
                    lines.map( line -> toRow( params, line ) ).
                        forEach( csvDocument::add );
                } ).
                build();
            RcdTextFileService.read( readTextFileParams );
            return csvDocument;
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading CSV document", e );
        }
    }

    private RcdSimpleCsvRow toRow( final RcdReadCsvDocumentParams params, final String line )
    {
        return new RcdSimpleCsvRow( Arrays.asList( line.split( params.getSeparator() ) ) );
    }
}
