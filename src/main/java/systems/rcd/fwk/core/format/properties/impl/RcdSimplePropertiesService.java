package systems.rcd.fwk.core.format.properties.impl;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import systems.rcd.fwk.core.format.properties.RcdPropertiesService;
import systems.rcd.fwk.core.io.file.RcdTextFileService;

public class RcdSimplePropertiesService
    implements RcdPropertiesService
{
    private final static Pattern PATTERN = Pattern.compile( "\\s*([^\\s#!]+)\\s*=\\s*(\\S+)\\s*" );

    @Override
    public Map<String, String> instRead( final Path path )
        throws Exception
    {
        Map<String, String> properties = new HashMap<>();
        RcdTextFileService.readAsStream( path, lines -> {
            lines.forEach( line -> {
                final Matcher matcher = PATTERN.matcher( line );
                if ( matcher.matches() )
                {
                    properties.put( matcher.group( 1 ), matcher.group( 2 ) );
                }
            } );
        } );
        return properties;
    }
}
