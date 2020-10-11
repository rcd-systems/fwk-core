package systems.rcd.fwk.core.io.file.params;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import systems.rcd.fwk.core.util.zip.Preconditions;

public class RcdWriteTextFileParams
{
    private Path path;

    private Charset charset;

    private String content;

    private Iterable<String> contentLines;

    private RcdWriteTextFileParams( final Builder builder )
    {
        Preconditions.checkNotNull( builder.path, "Path cannot be null" );
        Preconditions.check( builder.content != null || builder.contentLines != null, "The content must be set" );
        path = builder.path;
        charset = builder.charset == null ? StandardCharsets.UTF_8 : builder.charset;
        content = builder.content;
        contentLines = builder.contentLines;
    }

    public Path getPath()
    {
        return path;
    }

    public Charset getCharset()
    {
        return charset;
    }

    public String getContent()
    {
        return content;
    }

    public Iterable<String> getContentLines()
    {
        return contentLines;
    }

    public static RcdWriteTextFileParams from( final Path path, final String content )
    {
        return newBuilder().
            path( path ).
            content( content ).
            build();
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static final class Builder
    {
        private Path path;

        private Charset charset;

        private String content;

        private Iterable<String> contentLines;

        private Builder()
        {
        }

        public Builder path( final Path path )
        {
            this.path = path;
            return this;
        }

        public Builder charset( final Charset charset )
        {
            this.charset = charset;
            return this;
        }

        public Builder content( final String content )
        {
            this.content = content;
            return this;
        }

        public Builder contentLines( final Iterable<String> contentLines )
        {
            this.contentLines = contentLines;
            return this;
        }

        public RcdWriteTextFileParams build()
        {
            return new RcdWriteTextFileParams( this );
        }
    }
}
