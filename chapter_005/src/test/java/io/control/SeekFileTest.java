package io.control;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SeekFileTest {
    @Test
    public void whenSeekFileByMaskEquals() throws IOException {
        final String args = getCommandsMaskEquals();
        final SeekFile seeker = new SeekFile(args.split(" "));
        seeker.writeSeekDataToFile();
        final var path = getPathTmpFile();
        final var result = getResultFromTmpFile(path);
        final var expected = "white.txt";
        assertThat(result, is(expected));

    }

    @Test
    public void whenSeekFileByMaskContains() throws IOException {
        final String args = getCommandsMaskContains();
        final SeekFile seeker = new SeekFile(args.split(" "));
        seeker.writeSeekDataToFile();
        final var path = getPathTmpFile();
        final var result = getResultFromTmpFile(path);
        final String[] expected = {"black.txt", "red.txt", "white.txt"};
        assertThat(result.split(" "), arrayContainingInAnyOrder(expected));

    }

    @Test
    public void whenSeekFileByMaskRegex() throws IOException {
        final String args = getCommandsMaskRegex();
        final SeekFile seeker = new SeekFile(args.split(" "));
        seeker.writeSeekDataToFile();
        final var path = getPathTmpFile();
        final var result = getResultFromTmpFile(path);
        final String[] expected = {"black.txt", "red.txt", "white.txt"};
        assertThat(result.split(" "), arrayContainingInAnyOrder(expected));

    }

    private String getResultFromTmpFile(final String path) throws
            IOException {
        String result = "";
        final BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        while (reader.read() != -1) {
            result = reader.readLine();
        }
        reader.close();
        if (!new File(path).delete()) {
            new File(path).deleteOnExit();
        }
        return result;
    }

    private String getPathTmpFile() {
        final var tmpDir = System.getProperty("java.io.tmpdir");
        String path = "";
        final File files = new File(tmpDir);
        final var dir = Objects.requireNonNull(files.listFiles());
        for (File file : dir) {
            if (file.getName().contains("SeekFile")) {
                path = file.getAbsolutePath();
                break;
            }
        }
        return path;
    }

    private String getCommandsMaskEquals() {
        return new StringBuilder()
                .append("java -jar seek.jar")
                .append(" -d ")
                .append("config")
                .append(" -n white.txt -f")
                .append(" -o SeekFile")
                .toString();
    }

    private String getCommandsMaskContains() {
        return new StringBuilder()
                .append("java -jar seek.jar")
                .append(" -d ")
                .append("config")
                .append(" -n .txt -m")
                .append(" -o SeekFile")
                .toString();
    }

    private String getCommandsMaskRegex() {
        return new StringBuilder()
                .append("java -jar seek.jar")
                .append(" -d ")
                .append("config")
                .append(" -n .*.txt -r")
                .append(" -o SeekFile")
                .toString();
    }
}
