package io.manager.tracker;

import io.manager.menu.Menu;
import io.manager.menu.MenuClient;

import java.io.BufferedWriter;
import java.io.File;
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
    }

    /**
     * Method get out query to server after wash.
     *
     * @param query query
     */
    private void outQuery(final String[] query) {
        final var ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(query).forEach(s -> sb.append(s).append(" "));
        try {
            this.menu.out(sb.toString().trim() + ln);
        } catch (IOException e) {
            System.out.println("Mistake in-out.oQ");
        }

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
        if (!answer.equals("")) {
            if (answer.equals("exit")) {
                answer = "exit";
            } else if (!(answer.length() < 2)) {
                answer = answer.substring(1);
            }
        }
        return answer;
    }

    /**
     * Inner class Ext.
     * Check and sent command "exit" to server.
     * Exit server.
     */
    private class Ext extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 1;
            if (query.length != sizeQuery) {
                outRefuse();
            } else {
                outQuery(query);
            }
            return getAnswer();
        }
    }

    /**
     * Inner class Cd.
     * Check command and sent "cd" to server.
     * Shift path in server.
     */
    private class Cd extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 2;
            if (query.length != sizeQuery) {
                outRefuse();
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
    private class Dir extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            final var sizeQuery = 1;
            if (query.length != sizeQuery) {
                outRefuse();
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
                outRefuse();
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

        /**
         * Method loads file to server, client side.
         *
         * @param file file
         */
        private void load(final File file) {

            try {
                final var content = Files.readString(file.toPath());
                menu.out(content);
            } catch (IOException e) {
                System.out.println("Mistake in-out.load cAPI.");
            }

        }

    }

    /**
     * Method out line "refuse" to server.
     */
    private void outRefuse() {
        try {
            menu.out("refuse" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
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
                outRefuse();
            } else {
                final var qFile = query[1];
                final var qDir = query[2];
                final File file = new File(qDir + "/" + qFile);
                final File dir = new File(qDir);
                if (dir.isDirectory()) {
                    outQuery(query);
                    unload(file);
                }
            }
            return getAnswer();
        }

        /**
         * Method unloads file from server, client side.
         *
         * @param file file
         */
        private void unload(final File file) {
            try (BufferedWriter fos = Files.newBufferedWriter(file.toPath())) {
                fos.write(menu.in());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inner class Refuse.
     * Answer for illegal client command.
     */
    private class Refuse extends BaseAction {
        @Override
        public final String execute(final String[] query) {
            outRefuse();
            return getAnswer();
        }
    }
}
