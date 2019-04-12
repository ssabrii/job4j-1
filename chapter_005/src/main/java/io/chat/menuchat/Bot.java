package io.chat.menuchat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bot.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/12/2019
 */
final class Bot {
    /**
     * field list get data from chat file.
     */
    private static List<String> botList = new ArrayList<>();

    /**
     * Constructor util class.
     */
    private Bot() {
    }

    /**
     * Method get path chat file.
     *
     * @return path chat file
     */
    public static String getPathBot() {
        return "C:/projects/job4j/chapter_005/src/main/java/io/chat/source/"
                + "bot.txt";
    }

    /**
     * Method get random answer from chat list.
     *
     * @return random answer from chat list
     */
    public static String getBotAnswer() {
        Random any = new Random();
        return botList.get(any.nextInt(botList.size()));
    }

    /**
     * Method open file reader and read chat file to list.
     *
     * @param pathBotFile path chat file
     * @throws IOException exception
     */
    public static void getDataBotFile(final String pathBotFile)
            throws IOException {
        BufferedReader readerBot = new BufferedReader(
                new FileReader(pathBotFile));
        String tmp;
        while ((tmp = readerBot.readLine()) != null) {
            botList.add(tmp);
        }
        readerBot.close();
    }
}
