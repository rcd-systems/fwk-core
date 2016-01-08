package systems.rcd.fwk.core.io.file.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

import systems.rcd.fwk.core.io.file.RcdFileService;

public class RcdNioFileService implements RcdFileService {
    private Charset charset = StandardCharsets.UTF_8;

    @Override
    public String instReadAsString(final Path path) throws IOException {
        final byte[] allBytes = Files.readAllBytes(path);
        return new String(allBytes, charset);
    }

    @Override
    public void instReadAsStream(final Path path, final Consumer<Stream<String>> reader) throws IOException {
        try (final Stream<String> lines = Files.lines(path, charset)) {
            reader.accept(lines);
        }
    }

    public void setCharset(final Charset charset) {
        this.charset = charset;
    }
}
