package io.tabooword;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class AnalyzeTest {
    private static final File SOURCE = new File("config/server.log");
    private static final File TARGET = new File("config/unavailable.log");
    private final Analyze analyze = new Analyze();

    @Before
    public void before() {
        if (SOURCE.exists()) {
            if (SOURCE.delete()) {
                try (PrintWriter out = new PrintWriter(new FileOutputStream(SOURCE))) {
                    out.println("200 10:56:01");
                    out.println("500 10:57:01");
                    out.println("400 10:58:01");
                    out.println("200 10:59:01");
                    out.println("500 11:01:02");
                    out.println("200 11:02:02");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenUnavailableOk() {
        this.analyze.unavailable(SOURCE.getAbsolutePath(), TARGET.getAbsolutePath());
    }
    @Test
    public void whenUnavailableFallFileSource() {
        this.analyze.unavailable("config", TARGET.getAbsolutePath());
    }
    @Test
    public void whenUnavailableFallFileTarget() {
        this.analyze.unavailable(SOURCE.getAbsolutePath(), "config");
    }
    @Test
    public void whenUnavailableFallFilesSourceAndTarget() {
        this.analyze.unavailable("config", "config");
    }
}
