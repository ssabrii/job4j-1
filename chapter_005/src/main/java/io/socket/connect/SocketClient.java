package io.socket.connect;

import io.socket.input.ConsoleInput;
import io.socket.input.Input;
import io.socket.input.ValidateInput;

import java.io.IOException;
import java.net.Socket;

/**
 * SocketClient.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/13/2019
 */
public class SocketClient {
    /**
     * field socket.
     */
    private final Socket socket;
    /**
     * field input data user.
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
     * Method start Client.
     *
     * @throws IOException exception
     */
    public final void startClient() throws IOException {
        SocketMenu menu = new SocketMenu();
        menu.setIn(this.socket);
        menu.setOut(this.socket);
        menu.fillStorage();
        String client;
        String answer;
        do {
            client = this.input.request();
            if (!"exit".equals(client)) {
                menu.out(client);
                answer = menu.in();
                menu.print(answer);
            } else {
                menu.out(client);
            }
        } while (!"exit".equals(client));
        menu.print("");
    }

    /**
     * Method point to program.
     *
     * @param args args
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        final var port = 2000;
        final Socket socket = new Socket("127.0.0.1", port);
        new SocketClient(socket, new ValidateInput(new ConsoleInput()))
                .startClient();

    }
}
