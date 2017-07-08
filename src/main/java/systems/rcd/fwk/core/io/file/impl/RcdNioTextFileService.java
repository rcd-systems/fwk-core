package systems.rcd.fwk.core.io.file.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.exc.RcdException;
import systems.rcd.fwk.core.io.file.RcdTextFileService;

public class RcdNioTextFileService
    implements RcdTextFileService
{
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public String instReadAsString( final Path path )
    {
        try
        {
            final byte[] allBytes = Files.readAllBytes( path );
            return new String( allBytes, charset );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }

    @Override
    public void instReadAsStream( final Path path, final Consumer<Stream<String>> reader )
    {
        try (final Stream<String> lines = Files.lines( path, charset ))
        {
            reader.accept( lines );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }

    @Override
    public void instWrite( final Path path, final String content )
    {
        try
        {
            final byte[] bytes = content.getBytes( charset );
            Files.write( path, bytes, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }

    @Override
    public void instWrite( final Path path, final Iterable<String> content )
    {
        try
        {
            Files.write( path, content, charset, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND );
        }
        catch ( Exception e )
        {
            throw new RcdException( "Error while reading file", e );
        }
    }
}
