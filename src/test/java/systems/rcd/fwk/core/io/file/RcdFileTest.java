package systems.rcd.fwk.core.io.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import junit.framework.Assert;

import org.junit.Test;

public class RcdFileTest {

    private static final String FILE_DIRECTORY = "src/test/resources/systems/rcd/fwk/core/io/file";
    private static final Path UNIX_PATH = Paths.get(FILE_DIRECTORY, "input-unix.txt");
    private static final Path WIN_PATH = Paths.get(FILE_DIRECTORY, "input-win.txt");

    @Test
    public void testRcdNioFileService() throws Exception {
        final String unixContentAsString = RcdFileService.readAsString(UNIX_PATH);
        Assert.assertEquals("Some content\nこんにちわ\næøå", unixContentAsString);
        final String winContentAsString = RcdFileService.readAsString(WIN_PATH);
        Assert.assertEquals("Some content\r\nこんにちわ\r\næøå", winContentAsString);

        final List<String> expectedList = Arrays.asList(new String[] { "Some content", "こんにちわ", "æøå" });

        RcdFileService.readAsStream(UNIX_PATH, lines -> {
            final List<String> actualList = lines.collect(Collectors.toList());
            Assert.assertEquals(expectedList, actualList);
        });

        RcdFileService.readAsStream(WIN_PATH, lines -> {
            final List<String> actualList = lines.collect(Collectors.toList());
            Assert.assertEquals(expectedList, actualList);
        });
    }
}
