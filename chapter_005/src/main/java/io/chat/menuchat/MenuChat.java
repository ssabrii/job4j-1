package io.chat.menuchat;

import io.chat.ouput.Output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.chat.menuchat.Bot.getDataBotFile;

/**
 * MenuTracker.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/11/2019
 */
public class MenuChat {
    /**
     * field output.
     */
    private final Output out;
    /**
     * field flag for stop input chat side dialog.
     */
    private boolean isStop = false;
    /**
     * field map action with choice action bot.
     */
    private Map<String, UserAction> action = new HashMap<>();

    /**
     * Constructor.
     *
     * @param aOut output
     */
    public MenuChat(final Output aOut) {
        this.out = aOut;
    }

    /**
     * Method fill map for action.
     */
    public final void fillAction() {
        this.action = Map.of(
                "stop", new Stop(),
                "continue", new Continue(),
                "end", new End(),
                "any", new Any());
    }

    /**
     * Method open log file for write.
     *
     * @throws IOException exception
     */
    public final void openLogFile() throws IOException {
        this.out.openFile();
    }

    /**
     * Method get data chat file.
     *
     * @param pathBot path chat file
     * @throws IOException exception
     */
    public final void getBotFile(final String pathBot) throws IOException {
        getDataBotFile(pathBot);
    }

    /**
     * Method select key for execute.
     *
     * @param key user input
     * @throws IOException exception
     */
    public final void select(final String key) throws IOException {
        Objects.requireNonNull(key, "must not be null");
        UserAction userAction = this.action
                .getOrDefault(key, this.action.get("any"));
        userAction.execute(this.out, key);
    }
        /**
         * Inner class Stop.
         */
        public class Stop extends BaseAction {
            @Override
            public final void execute(final Output pOut, final String pUserKey)
                    throws IOException {
                isStop = true;
                pOut.writeData("." + pUserKey);
            }
        }

        /**
         * Inner class Continue.
         */
        public class Continue extends BaseAction {
            @Override
            public final void execute(final Output pOut, final String pUserKey)
                    throws IOException {
                isStop = false;
                pOut.writeData("." + pUserKey);
                String botAnswer = Bot.getBotAnswer();
                System.out.println(botAnswer);
                pOut.writeData("." + botAnswer);
            }
        }

        /**
         * Inner class End.
         */
        public class End extends BaseAction {
            @Override
            public final void execute(final Output pOut, final String pUserKey)
                    throws IOException {
                pOut.writeData("." + pUserKey);
                pOut.closeFile();
            }
        }

        /**
         * Inner class Any.
         */
        public class Any extends BaseAction {
            @Override
            public final void execute(final Output pOut, final String pUserKey)
                    throws IOException {
                if (isStop) {
                    pOut.writeData("." + pUserKey);
                } else {
                    pOut.writeData("." + pUserKey);
                    String botAnswer = Bot.getBotAnswer();
                    System.out.println(botAnswer);
                    pOut.writeData("." + botAnswer);

                }
            }

        }
    }
