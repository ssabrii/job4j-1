package io.chat.ouput;

import java.io.IOException;

/**
 * Output.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/12/2019
 */
public interface Output {
    /**
     * Method open log file for write.
     *
     * @throws IOException exception
     */
    void openFile() throws IOException;

    /**
     * Method close log file for write.
     *
     * @throws IOException exception
     */
    void closeFile() throws IOException;

    /**
     * Method write bot to log file.
     *
     * @param data data for write to log file
     * @throws IOException exception
     */
    void writeData(String data) throws IOException;
}
