package systems.rcd.fwk.core.io.file.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.io.file.RcdTextFileService;

public class RcdNioTextFileService
    implements RcdTextFileService
{
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public String instReadAsString( final Path path )
        throws IOException
    {
        final byte[] allBytes = Files.readAllBytes( path );
        return new String( allBytes, charset );
    }

    @Override
    public void instReadAsStream( final Path path, final Consumer<Stream<String>> reader )
        throws IOException
    {
        try (final Stream<String> lines = Files.lines( path, charset ))
        {
            reader.accept( lines );
        }
    }

    @Override
    public void instWrite( final Path path, final String content )
        throws IOException
    {
        final byte[] bytes = content.getBytes( charset );
        Files.write( path, bytes, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND );
    }

    @Override
    public void instWrite( final Path path, final Iterable<String> content )
        throws IOException
    {
        Files.write( path, content, charset, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND );
    }
}
