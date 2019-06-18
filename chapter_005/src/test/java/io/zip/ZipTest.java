package io.zip;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class ZipTest {
    @Test
    public void whenProToZipOk() throws IOException {
        final var SP = File.separator;
        final File source = new File("config");
        var property = System.getProperty("user.dir");
        if (!property.endsWith(SP)) {
            property += SP;
        }
        var out = property + "tmp" + SP + "archive.zip";
        final var string = "java -jar pack.jar -d " + source.getAbsolutePath() + " -e *.txt -o " + out;
        final String[] args = string.split(" ");
        new Zip(args).archive();
        final File zip = new File(out);
        final Path expectedZipFile = Paths.get(zip.getAbsolutePath());
        assertTrue(Files.exists(expectedZipFile));
    }
}
