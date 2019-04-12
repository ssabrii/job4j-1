package io.chat.menuchat;

import io.chat.inputs.ConsoleInput;
import io.chat.inputs.Input;
import io.chat.inputs.ValidateInput;
import io.chat.ouput.Log;

import java.io.IOException;

import static io.chat.menuchat.Bot.getPathBot;
import static io.chat.ouput.Log.getPathLog;

/**
 * ChatBot.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/10/2019
 */
public class Chat {
    /**
     * field input.
     */
    private final Input input;
    /**
     * field file chat.
     */
    private final String botTxt;
    /**
     * field file log.
     */
    private final String logTxt;

    /**
     * Constructor.
     *
     * @param aInput  input
     * @param aBotTxt file chat
     * @param aLogTxt file log
     */
    public Chat(final Input aInput,
                final String aBotTxt, final String aLogTxt) {
        this.input = aInput;
        this.botTxt = aBotTxt;
        this.logTxt = aLogTxt;
    }

    /**
     * Method run bot with chat.
     *
     * @throws IOException exception
     */
    public final void chat() throws IOException {
        Log aOut = new Log(this.logTxt);
        MenuChat menu = new MenuChat(aOut);
        menu.getBotFile(this.botTxt);
        menu.openLogFile();
        menu.fillAction();
        String key;
        do {
            key = input.fromUser();
            menu.select(key);
        } while (!"end".equals(key));
    }

    /**
     * Point entry to program.
     *
     * @param args args
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        new Chat(new ValidateInput(new ConsoleInput()),
                getPathBot(), getPathLog()).chat();
    }
}
