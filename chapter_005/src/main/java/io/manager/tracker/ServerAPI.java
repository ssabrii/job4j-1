package io.manager.tracker;

import io.manager.menu.Menu;
import io.manager.menu.MenuServer;
import io.manager.properties.Root;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * SocketMenu.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public class ServerAPI {
    /**
     * field path root.
     */
    private final Root root = new Root();
    /**
     * field server menu.
     */
    private final Menu menu = new MenuServer();
    /**
     * field keeper names command from client to server.
     */
    private Map<String, BaseAction> catalog = new HashMap<>();
    /**
     * field stores components server path.
     * and helps build the current server path.
     */
    private final ComponentsAPI components = new ComponentsAPI();

    /**
     * Method get query.
     *
     * @param query query.
     * @return query by array
     */
    public final String query(final String query) {
        final var keys = query.trim().split(" ");
        return this.catalog.getOrDefault(keys[0], new Refuse()).execute(keys);
    }


    /**
     * Method create catalog sever/client command.
     */
    public final void fillCatalogAnswers() {
        this.catalog = Map.of(
                "cd", new Cd(),
                "dir", new Dir(),
                "exit", new Ext(),
                "load", new Load(),
                "refuse", new Refuse(),
                "unload", new UnLoad());
    }

    /**
     * Method gets input stream.
     *
     * @return input stream
     */
    public final String in() {
        var client = "";
        try {
            client = this.menu.in();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * Method gets output stream.
     *
     * @param answer line for output
     */
    public final void out(final String answer) {
        final var ln = System.lineSeparator();
        try {
            this.menu.out(answer + ln);
        } catch (IOException e) {
            System.out.println("Mistake in-out.sAPI");
        }
    }

    /**
     * Method print line.
     *
     * @param line line for print
     */
    public final void print(final String line) {
        this.menu.print(line);
    }

    /**
     * Method connect client server.
     * and get socket.
     *
     * @param aSocket socket
     */
    public final void connect(final Socket aSocket) {
        this.menu.connect(aSocket);
    }

    /**
     * Method set root catalog in server.
     */
    public final void setRoot() {
        final String[] split = this.root.getRootCatalog().split("/");
        this.components.setPathComponents(split);
    }

    /**
     * Inner class Ext.
     * Run disconnect with client.
     * Exit server.
     */
    private class Ext extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            return "exit";
        }
    }

    /**
     * Inner class Cd.
     * Shift between directory.
     * Shift path in server.
     */
    private class Cd extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var path = "";
            final var demand = checkValue(query);
            switch (demand) {
                case "slash":
                    path = root.getRootCatalog();
                    break;
                case "dot":
                    final var offset = 1;
                    path = components.collectByOffset(offset);
                    break;
                case "filename":
                    final var fileName = query[1];
                    path = components.collectByName(fileName);
                    break;
                default:
                    var warn = "Refuse.illegal command.@";
                    path = warn + components.collectByOffset(0);
            }
            return path;
        }

        /**
         * Inner method check input value for right way command.
         *
         * @param query input query
         * @return right or bad command from client
         */
        private String checkValue(final String[] query) {
            var result = "";
            final var sb = new StringBuilder();
            IntStream.range(1, query.length).forEach(i -> sb.append(query[i]));
            final var value = sb.toString();
            if (value.equals("/")) {
                result = "slash";
            } else if (value.equals("..")) {
                result = "dot";
            } else if (!value.contains("/") && !value.contains("..")) {
                result = "filename";
            }
            return result;
        }
    }

    /**
     * Inner class Dir.
     * Show files and directory path in server.
     */
    private class Dir extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var path = components.collectByOffset(0);
            File dir = new File(path);
            var pack = "";
            File[] files = dir.listFiles();
            if (Objects.requireNonNull(files).length != 0) {
                for (final File file : files) {
                    if (file.isDirectory()) {
                        pack = pack.concat(file.getName() + " <DIR>@");
                    } else {
                        pack = pack.concat(file.getName() + " <file>@");
                    }
                }
            } else {
                pack = "Directory is empty.@";
            }
            return pack + root.getRootCatalog();
        }
    }

    /**
     * Inner class Load.
     * Loads the file to server.
     */
    private class Load extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var qFile = query[1];
            final var qPath = components.collectByOffset(0);
            var path = qPath + "/" + qFile;
            var status = load(path);
            return status + qPath;
        }

        /**
         * Method load file to server.
         *
         * @param path path
         * @return status loading
         */
        private String load(final String path) {
            var status = "False load.@";
            try (BufferedWriter fos = Files.newBufferedWriter(Path.of(path))) {
                fos.write(menu.in());
                fos.flush();
                status = "Load OK.@";
            } catch (IOException e) {
                status = "Mistake in-out.LOS.@";
            }
            return status;
        }
    }

    /**
     * Inner class UnLoad.
     * Unloads the file from server.
     */
    private class UnLoad extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var qFile = query[1];
            final var qDir = components.collectByOffset(0);
            final var path = qDir + "/" + qFile;
            final var status = unload(path);
            return status + qDir;
        }

        /**
         * Method unloads file from server.
         *
         * @param path path
         * @return status unloading
         */
        private String unload(final String path) {
            var status = "Unload false.@";
            try {
                final var content = Files.readString(Path.of(path));
                menu.out(content);
                status = "UnLoad OK.@";
            } catch (IOException e) {
                status = "Mistake in-out.ULS.@";
            }
            return status;
        }
    }

    /**
     * Inner class Refuse.
     * Answer on illegal command.
     */
    private class Refuse extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var warn = "Refuse.Illegal command.@";
            final var ln = System.lineSeparator();
            return new StringBuilder()
                    .append(warn)
                    .append(components.collectByOffset(0))
                    .append(ln)
                    .toString();
        }
    }
}
