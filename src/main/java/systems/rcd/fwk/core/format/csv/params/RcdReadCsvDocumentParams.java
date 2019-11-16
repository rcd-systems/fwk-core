package systems.rcd.fwk.core.format.csv.params;

import java.nio.file.Path;

import systems.rcd.fwk.core.util.zip.Preconditions;

public class RcdReadCsvDocumentParams
{
    private Path path;

    private String separator;

    private boolean skipHeader;

    private RcdReadCsvDocumentParams( final Builder builder )
    {
        Preconditions.checkNotNull( builder.path, "Path cannot be null" );
        path = builder.path;
        separator = builder.separator == null ? "," : builder.separator;
        skipHeader = builder.skipHeader;
    }

    public Path getPath()
    {
        return path;
    }

    public String getSeparator()
    {
        return separator;
    }

    public boolean isSkipHeader()
    {
        return skipHeader;
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static final class Builder
    {
        private Path path;

        private String separator;

        private boolean skipHeader = false;

        private Builder()
        {
        }

        public Builder path( final Path path )
        {
            this.path = path;
            return this;
        }

        public Builder separator( final String separator )
        {
            this.separator = separator;
            return this;
        }

        public Builder skipHeader( final boolean skipHeader )
        {
            this.skipHeader = skipHeader;
            return this;
        }

        public RcdReadCsvDocumentParams build()
        {
            return new RcdReadCsvDocumentParams( this );
        }
    }
}
