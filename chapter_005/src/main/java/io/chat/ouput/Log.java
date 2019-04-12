package io.chat.ouput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Log.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/12/2019
 */
public class Log extends Out {
    /**
     * field output data to log file.
     */
    private BufferedWriter writerLog;
    /**
     * field path log file.
     */
    private final String pathLog;

    /**
     * Constructor.
     *
     * @param aPathLog path log file
     */
    public Log(final String aPathLog) {
        this.pathLog = aPathLog;
    }

    /**
     * Method get path log file for console.
     *
     * @return path log file
     */
    public static String getPathLog() {
        return "C:/projects/job4j/chapter_005/src/main/java/io/chat/source/"
                + "log.txt";
    }

    @Override
    public final void openFile() throws IOException {
        this.writerLog = new BufferedWriter(new FileWriter(this.pathLog));
    }

    @Override
    public final void closeFile() throws IOException {
        this.writerLog.close();
    }

    @Override
    public final void writeData(final String data) throws IOException {
        this.writerLog.write(data);
    }
}
