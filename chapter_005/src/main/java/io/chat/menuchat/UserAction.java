package io.chat.menuchat;

import io.chat.ouput.Output;

import java.io.IOException;

/**
 * UserAction.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */
public interface UserAction {
    /**
     * Method read dialog in bot and write it in log file.
     *
     * @param out output
     * @param key input user
     * @throws IOException exception
     */
    void execute(Output out, String key) throws IOException;
}
