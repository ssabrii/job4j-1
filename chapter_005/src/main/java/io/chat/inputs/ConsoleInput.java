package io.chat.inputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ConsoleInput.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */
public class ConsoleInput implements Input {
    /**
     * field get side user dialog.
     */
    private final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    @Override
    public final String fromUser() throws IOException {
        return reader.readLine();
    }
}
