package io.manager.assembly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * Assembling.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public class Assembling implements Assembly {
    /**
     * field reader.
     */
    private BufferedReader reader;
    /**
     * field writer.
     */
    private PrintWriter writer;

    @Override
    public final void setInputReader(final Socket socket) throws IOException {
        Objects.requireNonNull(socket, "must not be null");
        this.reader = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    @Override
    public final void setOutputWriter(final Socket socket) throws IOException {
        Objects.requireNonNull(socket, "must not be null");
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public final void outOutputWriter(final String fileName) {
        this.writer.println(fileName);

    }

    @Override
    public final String inInputReader() throws IOException {
        return this.reader.readLine();
    }
}
