package io.manager.sockets;

import io.manager.properties.Properties;
import io.manager.properties.PropertySocket;
import io.manager.tracker.ServerAPI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketSever.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public class SocketServer {
    /**
     * field socket.
     */
    private final Socket socket;

    /**
     * Constructor.
     *
     * @param aSocket socket
     */
    public SocketServer(final Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method start server.
     *
     * @throws IOException io exception
     */
    public final void startServer() throws IOException {
        ServerAPI server = new ServerAPI();
        server.connect(this.socket);
        server.fillCatalogAnswers();
        server.setRoot();
        String client;
        String answer;
        do {
            server.print("wait command...");
            client = server.in();
            server.print(client);
            answer = server.query(client);
            if (!answer.equals("exit")) {
                server.out(answer);
                server.print(answer);
            } else {
                server.print(answer);
            }
        } while (!answer.equals("exit"));
        this.socket.close();
    }

    /**
     * Method point enter to program.
     *
     * @param args args
     * @throws IOException io exception
     */
    public static void main(final String[] args) throws IOException {
        final Properties prop = new PropertySocket();
        final Socket socket = new ServerSocket(
                Integer.valueOf(prop.port())).accept();
        new SocketServer(socket).startServer();
    }
}
