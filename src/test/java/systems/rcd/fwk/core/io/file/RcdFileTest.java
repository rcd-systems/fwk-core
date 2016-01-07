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
    private static final Path INPUT_PATH = Paths.get(FILE_DIRECTORY, "input.txt");

    @Test
    public void testRcdNioFileService() throws Exception {
        final String unixContentAsString = RcdFileService.readAsString(INPUT_PATH);
        Assert.assertEquals("Some content\nこんにちわ\næøå", unixContentAsString);

        final List<String> expectedList = Arrays.asList(new String[] { "Some content", "こんにちわ", "æøå" });
        RcdFileService.readAsStream(INPUT_PATH, lines -> {
            final List<String> actualList = lines.collect(Collectors.toList());
            Assert.assertEquals(expectedList, actualList);
        });
    }
}
