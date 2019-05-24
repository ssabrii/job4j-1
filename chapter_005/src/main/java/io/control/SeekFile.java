package io.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * SeekFile.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/11/2019
 */
public class SeekFile {
    /**
     * field array commands.
     */
    private final String[] args;

    /**
     * Constructor.
     *
     * @param aArgs array commands
     */
    public SeekFile(final String[] aArgs) {
        Objects.requireNonNull(aArgs, "must not be null");
        this.args = aArgs;
    }

    /**
     * Method gets and processes input parameter for right key command.
     *
     * @param key key command
     * @return result after processing
     */
    private String getParameter(final String key) {
        String result = "";
        for (int i = 0; i < this.args.length; i++) {
            if (this.args[i].equals(key)) {
                switch (key) {
                    case "-m":
                    case "-f":
                    case "-r":
                        break;
                    default:
                        result = this.args[i + 1];
                        break;
                }
                break;
            }
        }
        return result;
    }

    /**
     * Method return source directory for seeking by key -d.
     *
     * @return source directory
     */
    private File sourceDir() {
        StringBuilder path = new StringBuilder();
        String checkRoot = "";
        var userDir = System.getProperty("user.dir");
        if (!userDir.endsWith("/")) {
            userDir += "/";
        }
        if (!userDir.contains("chapter_005")) {
            checkRoot = "chapter_005/";
        }
        path.append(userDir);
        path.append(checkRoot);
        path.append(getParameter("-d"));
        return new File(path.toString());
    }

    /**
     * Method return the name of log file by key -o.
     *
     * @return the name of log file
     */
    private String log() {
        return getParameter("-o");
    }

    /**
     * Method return mask by key -n.
     *
     * @return the name of log file
     */
    private String mask() {
        return getParameter("-n");
    }

    /**
     * Method return the name of log file by kes -f,-m,-r.
     * key -f: full name
     * key -m: contains sequence
     * key -r: regex
     *
     * @return right key
     */
    private String maskWay() {
        String result = "";
        final List<String> line = Arrays.asList(this.args);
        if (line.contains("-m")) {
            result = "-m";
        } else if (line.contains("-f")) {
            result = "-f";
        } else if (line.contains("-r")) {
            result = "-r";
        }
        return result;
    }

    /**
     * Method return the name of seek file by masks.
     *
     * @return the names of files
     */
    private String seekerData() {
        final var dir = this.sourceDir();
        final var way = this.maskWay();
        final var mask = this.mask();
        var sb = new StringBuilder();
        if (!dir.exists() && !dir.isDirectory()) {
            final var tmp = this.getPathTmpFile();
            if (!new File(tmp).delete()) {
                new File(tmp).deleteOnExit();
            }
            throw new IllegalArgumentException("Missing path.");
        } else {
            switch (way) {
                case "-f":
                    sb = dirEquals(dir, mask);
                    break;
                case "-r":
                    sb = dirRex(dir, mask);
                    break;
                case "-m":
                    sb = dirMask(dir, mask);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * Method get data by key -f.
     *
     * @param dir  source directory
     * @param mask mask
     * @return seek the name of file
     */
    private StringBuilder dirEquals(final File dir, final String mask) {
        StringBuilder sb = new StringBuilder();
        try {
            Files.walk(dir.toPath())
                    .map(Path::getFileName)
                    .filter(i -> i.toString().equals(mask))
                    .forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     * Method get data by key -m.
     *
     * @param dir  source directory
     * @param mask mask
     * @return seek the name of file
     */
    private StringBuilder dirMask(final File dir, final String mask) {
        final StringBuilder sb = new StringBuilder();
        try {
            Files.walk(dir.toPath())
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(i -> i.contains(mask))
                    .forEach(i -> sb.append(i).append(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     * Method get data by key -r.
     *
     * @param dir  source directory
     * @param mask mask
     * @return seek the name of file
     */
    private StringBuilder dirRex(final File dir, final String mask) {
        final StringBuilder sb = new StringBuilder();
        final StringBuilder sa = new StringBuilder();
        try {
            Files.walk(dir.toPath())
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .forEach(i -> sa.append(i).append(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Pattern pattern = Pattern.compile(mask);
        for (String word : sa.toString().split(" ")) {
            if (pattern.matcher(word).find()) {
                sb.append(word);
                sb.append(" ");
            }
        }
        return sb;
    }

    /**
     * Method writes seek the name of file to tmp file.
     *
     * @throws IOException exception
     */
    public final void writeSeekDataToFile() throws IOException {
        final var tmpDir = System.getProperty("java.io.tmpdir");
        final var path = new File(tmpDir);
        final var log = this.log();
        final var tmp = File.createTempFile(log, ".log", path);
        try (FileWriter writer = new FileWriter(tmp, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            final var data = this.seekerData();
            if (!data.isEmpty()) {
                bw.write(" ");
                bw.write(data);
            } else {
                bw.write(" ");
                bw.write("Don't find file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method gets path tmp file.
     *
     * @return the path tmp file
     */
    private String getPathTmpFile() {
        final var tmpDir = System.getProperty("java.io.tmpdir");
        var tmp = "";
        final File files = new File(tmpDir);
        final var dir = Objects.requireNonNull(files.listFiles());
        final var log = this.log();
        for (File file : dir) {
            if (file.getName().contains(log)) {
                tmp = file.getAbsolutePath();
                break;
            }
        }
        return tmp;
    }

    /**
     * Method gets seek result data from tmp file.
     *
     * @return result data from tmp file
     *
     * @throws IOException exception
     */
    public final String getResultFromTmpFile()
            throws IOException {
        var result = "";
        final var tmp = this.getPathTmpFile();
        final BufferedReader reader = new BufferedReader(
                new FileReader(new File(tmp)));
        while (reader.read() != -1) {
            result = reader.readLine();
        }
        reader.close();
        if (!new File(tmp).delete()) {
            new File(tmp).deleteOnExit();
        }
        return result;
    }

    /**
     * Method gets help command.
     */
    public final void announce() {
        out.println("--------------------------");
        out.println("Program seek file by mask.");
        out.println("--------------------------");
        out.println("Command:");
        out.println("1.Run program: java -jar SeekFile.jar");
        out.println("2.Name source catalog for seek: -d  config");
        out.println("3.Mask: -n red.txt, .txt, .*.txt");
        out.println("4.Mask view: -f(full name ) -m(contains name ) -r(regex)");
        out.println("5.Name log file for result: -o  name");
        out.println("Example:");
        out.print("java -jar SeekFile.jar -d config -n red.txt -f -o SeekFile");
        out.println();
        out.println("java -jar SeekFile.jar -d config -n .txt -m -o SeekFile");
        out.print("java -jar SeekFile.jar -d config -n .*.txt -r -o SeekFile");
        out.println();
        out.println();
    }

    /**
     * Point enter to program.
     *
     * @param args array command
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        SeekFile seek = new SeekFile(args);
        seek.announce();
        seek.writeSeekDataToFile();
        String result;
        result = seek.getResultFromTmpFile();
        out.println("Program find file(s): " + result);
    }
}
