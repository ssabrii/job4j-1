package io.scanbymask;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchTestTwo {
    private final Search search = new Search();
    private final File fileName = new File("config");
    private final List<String> reqList = new ArrayList<>(Arrays.asList("txt", "jpg"));

    @Test
    public void whenFilesOk() throws IOException {
        Files.deleteIfExists(Paths.get("config/tmpdir/black.txt"));
        Files.deleteIfExists(Paths.get("config/tmpdir/pic.jpg"));
        Files.deleteIfExists(Paths.get("config/tmpdir/cooke/red.txt"));
        Files.deleteIfExists(Paths.get("config/tmpdir/cooke"));
        Files.deleteIfExists(Paths.get("config/white.txt"));
        Files.deleteIfExists(Paths.get("config/tmpdir"));
        final Path tmpdir = Files.createDirectory(Path.of("config/tmpdir"));
        final Path black = Files.createFile(Path.of("config/tmpdir/black.txt"));
        final Path pic = Files.createFile(Path.of("config/tmpdir/pic.jpg"));
        final Path cooke = Files.createDirectory(Path.of("config/tmpdir/cooke"));
        final Path red = Files.createFile(Path.of("config/tmpdir/cooke/red.txt"));
        final Path white = Files.createFile(Path.of("config/white.txt"));
        final List<File> result = this.search.files(fileName, reqList);
        assertThat(result, containsInAnyOrder(new File(black.toString()),
                new File(red.toString()),
                new File(pic.toString()),
                new File(white.toString())));
    }

    @Test
    public void whenFileFallFatahMorganaFolder() {
        final File fileName = new File("con");
        final List<File> result = this.search.files(fileName, reqList);
        final List<File> empty = new ArrayList<>();
        assertThat(result, is(empty));
    }

}
