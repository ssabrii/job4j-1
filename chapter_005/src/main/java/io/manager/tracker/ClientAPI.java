package io.manager.tracker;

import io.manager.menu.Menu;
import io.manager.menu.MenuClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ClientAPI.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/28/2019
 */
public class ClientAPI {
    /**
     * field menu client side.
     */
    private final Menu menu = new MenuClient();
    /**
     * field keeper name command from client to server.
     */
    private Map<String, BaseAction> catalog = new HashMap<>();
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
        return this.catalog.getOrDefault(keys[0], new Refuse()).execute(keys);
    }

    /**
     * Method create catalog sever/client command.
     */
    public final void fillCatalogAnswers() {
        this.catalog = Map.of(
                "cd", new Cd(),
                "dir", new Dir(),
                "ref", new Refuse(),
                "load", new Load(),
                "unload", new UnLoad(),
                "exit", new Ext());
    }

    /**
     * Method print line.
     *
     * @param line line
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
     * Method get out query to server after wash.
     *
     * @param query query
     */
    private void outQuery(final String[] query) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(query).forEach(s -> sb.append(s).append(" "));
        this.menu.out(sb.toString());

    }

    /**
     * Method get answer from server.
     *
     * @return server answer
     */
    private String getAnswer() {
        var answer = "";
        try {
            answer = this.menu.in();
        } catch (IOException e) {
            System.out.println("Mistake in-out.getAnswer");
        }
        return answer;
    }

    /**
     * Method loads file to server, client side.
     *
     * @param file file
     */
    private void load(final File file) {
        try (DataOutputStream dos = new DataOutputStream(
                this.socket.getOutputStream())) {
            final var content = Files.readAllBytes(file.toPath());
            dos.write(content);
        } catch (IOException e) {
            System.out.println("Mistake in-out.LOC.");
        }
    }

    /**
     * Method unloads file from server, client side.
     *
     * @param file file
     */
    private void unload(final File file) {
        try (DataInputStream dis = new DataInputStream(
                this.socket.getInputStream());
             FileOutputStream fos = new FileOutputStream(file)) {
            while (dis.available() > 0) {
                fos.write(dis.read());
            }
        } catch (IOException e) {
            System.out.println("Mistake in-out.ULC.");
        }
    }


    /**
     * Inner class Ext.
     * Check and sent command "exit" to server.
     * Exit server.
     */
    private class Ext extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            var answer = "";
            final var sizeQuery = 1;
            if (query.length != sizeQuery) {
                menu.out("refuse");
                return getAnswer();
            } else {
                outQuery(query);
                answer = "exit";
            }
            return answer;
        }
    }

    /**
     * Inner class Cd.
     * Check command and sent "cd" to server.
     * Shift path in server.
     */
    @SuppressWarnings("Duplicates")
    private class Cd extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 2;
            if (query.length != sizeQuery) {
                menu.out("refuse");
                return getAnswer();
            } else {
                outQuery(query);
            }
            return getAnswer();
        }
    }

    /**
     * Inner class Dir.
     * Check command and sent "dir" to server.
     */
    @SuppressWarnings("Duplicates")
    private class Dir extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 1;
            if (query.length != sizeQuery) {
                menu.out("refuse");
                return getAnswer();
            } else {
                outQuery(query);
            }
            return getAnswer();
        }
    }

    /**
     * Inner class Load.
     * Check command and sent "load" to server.
     */
    private class Load extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 3;
            if (query.length != sizeQuery) {
                menu.out("refuse");
                return getAnswer();
            } else {
                final var qDir = query[2];
                final var qFile = query[1];
                final File file = new File(qDir + "/" + qFile);
                if (file.exists()) {
                    outQuery(query);
                    load(file);
                }
            }
            return getAnswer();
        }
    }

    /**
     * Inner class UnLoad.
     * Check command and sent "unload" to server.
     */
    private class UnLoad extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 3;
            if (query.length != sizeQuery) {
                menu.out("refuse");
                return getAnswer();
            } else {
                final var qDir = query[2];
                final File file = new File(qDir);
                if (file.isDirectory()) {
                    outQuery(query);
                    unload(file);
                }
            }
            return getAnswer();
        }
    }

    /**
     * Inner class Refuse.
     * Answer for illegal client command.
     */
    private class Refuse extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            menu.out("refuse");
            return getAnswer();
        }
    }
}
