package systems.rcd.fwk.core.io.file.params;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.util.zip.Preconditions;

public class RcdReadTextFileParams
{
    private Path path;

    private Charset charset;

    private Consumer<String> contentConsumer;

    private Consumer<Stream<String>> linesConsumer;

    private RcdReadTextFileParams( final Builder builder )
    {
        Preconditions.checkNotNull( builder.path, "Path cannot be null" );
        Preconditions.check( builder.contentConsumer != null || builder.linesConsumer != null, "A consumer must be set" );
        path = builder.path;
        charset = builder.charset == null ? StandardCharsets.UTF_8 : builder.charset;
        contentConsumer = builder.contentConsumer;
        linesConsumer = builder.linesConsumer;
    }

    public Path getPath()
    {
        return path;
    }

    public Charset getCharset()
    {
        return charset;
    }

    public Consumer<String> getContentConsumer()
    {
        return contentConsumer;
    }

    public Consumer<Stream<String>> getLinesConsumer()
    {
        return linesConsumer;
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static final class Builder
    {
        private Path path;

        private Charset charset;

        private Consumer<String> contentConsumer;

        private Consumer<Stream<String>> linesConsumer;

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

        public Builder contentConsumer( final Consumer<String> stringConsumer )
        {
            this.contentConsumer = stringConsumer;
            return this;
        }

        public Builder linesConsumer( final Consumer<Stream<String>> streamConsumer )
        {
            this.linesConsumer = streamConsumer;
            return this;
        }

        public RcdReadTextFileParams build()
        {
            return new RcdReadTextFileParams( this );
        }
    }
}
