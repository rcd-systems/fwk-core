package systems.rcd.fwk.core.io.path.impl;

import java.io.File;
import java.nio.file.Path;

import systems.rcd.fwk.core.io.path.RcdPathService;

public class RcdSimplePathService
    implements RcdPathService
{
    private static final char UNIX_SEPARATOR_CHAR = '/';

    @Override
    public String instToUnixPathString( final Path path )
    {
        final String pathString = path.toString();
        if ( UNIX_SEPARATOR_CHAR == File.separatorChar )
        {
            return pathString;
        }
        else
        {
            return pathString.replace( File.separatorChar, UNIX_SEPARATOR_CHAR );
        }
    }
}
