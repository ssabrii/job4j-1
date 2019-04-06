package io.scanbymask;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchClearTest {
    private final SearchClear search = new SearchClear();
    private final File fileName = new File("config");
    private final List<String> reqList = new ArrayList<>(Arrays.asList("txt", "jpg"));

    @Test
    public void whenFilesOk() {
        String sp = File.separator;
        final List<File> result = this.search.files(fileName, reqList);
        final File black = new File("config" + sp + "tmpdir" + sp + "black.txt");
        final File red = new File("config" + sp + "tmpdir" + sp + "cooke" + sp + "red.txt");
        final File pic = new File("config" + sp + "tmpdir" + sp + "pic.jpg");
        final File white = new File("config" + sp + "white.txt");
        assertThat(result, containsInAnyOrder(black, red, pic, white));
    }

    @Test(expected = NullPointerException.class)
    public void whenFileFallFatahMorganaFolder() {
        final File fileName = new File("con");
        final List<File> result = this.search.files(fileName, reqList);
        final List<File> empty = new ArrayList<>();
        assertThat(result, is(empty));
    }
}