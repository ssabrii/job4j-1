package io.manager.assembly;

import java.io.IOException;
import java.net.Socket;

/**
 * Assembly.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public interface Assembly {

    /**
     * Method set output.
     *
     * @param socket socket
     * @throws IOException io exception
     */
    void setOutputWriter(final Socket socket) throws IOException;

    /**
     * Method set input.
     *
     * @param socket socket
     * @throws IOException io exception
     */
    void setInputReader(final Socket socket) throws IOException;

    /**
     * Method get out line.
     *
     * @param fileName line for out
     * @throws IOException ioException
     */
    void outOutputWriter(String fileName) throws IOException;

    /**
     * Method get in line.
     *
     * @return get line
     *
     * @throws IOException oi exception
     */
    String inInputReader() throws IOException;
}
