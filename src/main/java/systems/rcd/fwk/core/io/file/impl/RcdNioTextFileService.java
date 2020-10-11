package systems.rcd.fwk.core.io.file.impl;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.io.file.RcdTextFileService;
import systems.rcd.fwk.core.io.file.params.RcdReadTextFileParams;
import systems.rcd.fwk.core.io.file.params.RcdWriteTextFileParams;

public class RcdNioTextFileService
    implements RcdTextFileService
{
    @Override
    public void instRead( final RcdReadTextFileParams params )
    {
        if ( params.getLinesConsumer() != null )
        {
            readAsStream( params );
        }
        else
        {
            readAsString( params );
        }
    }

    public void readAsStream( final RcdReadTextFileParams params )
    {
        try (final Stream<String> lines = Files.lines( params.getPath(), params.getCharset() ))
        {
            params.getLinesConsumer().accept( lines );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }

    public void readAsString( final RcdReadTextFileParams params )
    {
        final String result;
        try
        {
            final byte[] allBytes = Files.readAllBytes( params.getPath() );
            result = new String( allBytes, params.getCharset() );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
        params.getContentConsumer().accept( result );
    }

    @Override
    public void instWrite( final RcdWriteTextFileParams params )
    {
        if ( params.getContent() != null )
        {
            writeContent( params );
        }
        else
        {
            writeContentLines( params );
        }
    }

    public void writeContent( final RcdWriteTextFileParams params )
    {
        try
        {
            final byte[] bytes = params.getContent().getBytes( params.getCharset() );
            Files.write( params.getPath(), bytes, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }

    public void writeContentLines( final RcdWriteTextFileParams params )
    {
        try
        {
            Files.write( params.getPath(), params.getContentLines(), params.getCharset(), StandardOpenOption.WRITE,
                         StandardOpenOption.CREATE, StandardOpenOption.APPEND );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }
}
