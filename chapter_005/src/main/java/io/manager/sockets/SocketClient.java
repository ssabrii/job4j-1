package io.manager.sockets;

import io.manager.input.Input;
import io.manager.input.InputConsole;
import io.manager.input.InputValidate;
import io.manager.properties.Properties;
import io.manager.properties.PropertySocket;
import io.manager.tracker.ClientAPI;

import java.io.IOException;
import java.net.Socket;

/**
 * SocketClient.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/27/2019
 */
public class SocketClient {
    /**
     * field socket.
     */
    private final Socket socket;
    /**
     * field option input.
     */
    private final Input input;

    /**
     * Constructor.
     *
     * @param aSocket socket
     * @param aInput  input
     */

    public SocketClient(final Socket aSocket, final Input aInput) {
        this.socket = aSocket;
        this.input = aInput;
    }

    /**
     * Method start client.
     *
     * @throws IOException io exception
     */
    public final void startClient() throws IOException {
        ClientAPI api = new ClientAPI();
        api.fillCatalogAnswers();
        api.connect(this.socket);
        String answer;
        String query;
        announce();
        api.print(api.query("cd /"));
        do {
            query = this.input.request();
            answer = api.query(query);
            api.print(answer);
        } while (!query.equals("exit"));
    }

    /**
     * Method announce client console command.
     */
    private void announce() {
        final var command = new StringBuilder()
                .append(" Use command:")
                .append(System.lineSeparator())
                .append(" \"cd /\"(root),")
                .append(" \"cd ..\"(up),")
                .append(" \"cd filename\"(down),")
                .append(" \"dir\" (show dir)")
                .append(System.lineSeparator())
                .append(" \"load filename pathClient\" "
                        + "(load to current server catalog)")
                .append(System.lineSeparator())
                .append(" \"unload filename pathClient\" "
                        + "(unload to catalog client)")
                .append(System.lineSeparator())
                .toString();
        System.out.print(command);
    }

    /**
     * Method point enter to program.
     *
     * @param args args
     * @throws IOException io exception
     */
    public static void main(final String[] args) throws IOException {
        final Properties prop = new PropertySocket("config/manager.properties");
        final Socket socket = new Socket(prop.ip(),
                Integer.valueOf(prop.port()));
        new SocketClient(socket, new InputValidate(new InputConsole()))
                .startClient();
    }
}

