package io.manager.tracker;

import io.manager.menu.Menu;
import io.manager.menu.MenuServer;
import io.manager.properties.Properties;
import io.manager.properties.PropertySocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
     * field socket.
     */
    private Socket socket;

    /**
     * Method get query.
     *
     * @param query query.
     * @return query by array
     */
    public final String query(final String query) {
        final var keys = query.trim().split(" ");
        return this.catalog.get(keys[0]).execute(keys);
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
        this.menu.out(answer);
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
        this.socket = aSocket;
    }

    /**
     * Method set root catalog in server.
     */
    public final void setRoot() {
        final Properties prop = new PropertySocket();
        final String[] split = prop.getRootCatalog().split("/");
        this.components.setPathComponents(split);
    }

    /**
     * Method load file to server.
     *
     * @param path path
     * @return status loading
     */
    public final String load(final String path) {
        var answer = "False load.@";
        try (DataInputStream dis = new DataInputStream(
                this.socket.getInputStream());
             FileOutputStream fos = new FileOutputStream(path)) {
            while (dis.available() > 0) {
                fos.write(dis.read());
            }
            answer = "Load OK.@";
        } catch (IOException e) {
            answer = "Mistake in-out.LOS.@";
        }

        return answer;
    }

    /**
     * Method unloads file from server.
     *
     * @param path path
     * @return status unloading
     */
    public final String unload(final String path) {
        var answer = "Unload false.@";
        try (DataOutputStream dos = new DataOutputStream(
                this.socket.getOutputStream())) {
            final var content = Files.readAllBytes(Path.of(path));
            dos.write(content);
            answer = "UnLoad OK.@";
        } catch (IOException e) {
            answer = "Mistake in-out.ULS.@";
        }
        return answer;
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
                    final PropertySocket prop = new PropertySocket();
                    path = prop.getRootCatalog();
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
     * Show files and directory for current path in server.
     */
    private class Dir extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var path = components.collectByOffset(0);
            File dir = new File(path);
            var pack = "";
            if (dir.length() != 0) {
                File[] files = dir.listFiles();
                for (final File file : Objects.requireNonNull(files)) {
                    if (file.isDirectory()) {
                        pack = pack.concat(file.getName() + " <DIR>@");
                    } else {
                        pack = pack.concat(file.getName() + " <file>@");
                    }
                }
            } else {
                pack = "Directory is empty.@";
            }
            return pack + path;
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
            var path = components.collectByOffset(0) + "/" + qFile;
            var status = load(path);
            return status + path;
        }
    }

    /**
     * Inner class UnLoad.
     * Unloads the file from server.
     */
    private class UnLoad extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var qFile = query[1];
            var path = components.collectByOffset(0) + "/" + qFile;
            var status = unload(path);
            return status + path;
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
            return warn + components.collectByOffset(0);
        }
    }
}
