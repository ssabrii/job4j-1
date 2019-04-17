package io.manager.menu;

import java.io.IOException;
import java.net.Socket;

/**
 * Menu.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/25/2019
 */
public interface Menu {

    /**
     * Method get input.
     *
     * @throws IOException io exception
     * @return input
     */
    String in() throws IOException;

    /**
     * Method get output.
     * @param answer answer
     */
    void out(final String answer);

    /**
     * Method print answer server or client.
     *
     * @param line answer
     */
    void print(final String line);
    /**
     * Method set connect with server.
     *
     * @param aSocket socket
     */
    void connect(final Socket aSocket);
}
