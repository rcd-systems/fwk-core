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
        throws Exception
    {
        return RcdContext.getService( RcdTextFileService.class ).instReadAsString( path );
    }

    static void readAsStream( final Path path, final Consumer<Stream<String>> reader )
        throws Exception
    {
        RcdContext.getService( RcdTextFileService.class ).instReadAsStream( path, reader );
    }

    static void write( final Path path, final String content )
        throws Exception
    {
        RcdContext.getService( RcdTextFileService.class ).instWrite( path, content );
    }

    static void write( final Path path, final Iterable<String> content )
        throws Exception
    {
        RcdContext.getService( RcdTextFileService.class ).instWrite( path, content );
    }

    String instReadAsString( final Path path )
        throws Exception;

    void instReadAsStream( final Path path, final Consumer<Stream<String>> consumer )
        throws Exception;

    void instWrite( Path path, String content )
        throws Exception;

    void instWrite( Path path, Iterable<String> content )
        throws Exception;

}
