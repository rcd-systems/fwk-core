package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.ctx.RcdContext;
import systems.rcd.fwk.core.ctx.RcdService;

public interface RcdTextFileService
    extends RcdService
{
    static String readAsString( final Path path )
    {
        return RcdContext.getService( RcdTextFileService.class ).instReadAsString( path );
    }

    static void readAsStream( final Path path, final Consumer<Stream<String>> reader )
    {
        RcdContext.getService( RcdTextFileService.class ).instReadAsStream( path, reader );
    }

    static void write( final Path path, final String content )
    {
        RcdContext.getService( RcdTextFileService.class ).instWrite( path, content );
    }

    static void write( final Path path, final Iterable<String> content )
    {
        RcdContext.getService( RcdTextFileService.class ).instWrite( path, content );
    }

    String instReadAsString( final Path path );

    void instReadAsStream( final Path path, final Consumer<Stream<String>> consumer );

    void instWrite( Path path, String content );

    void instWrite( Path path, Iterable<String> content );

}
