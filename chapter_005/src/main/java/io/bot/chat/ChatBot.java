package io.bot.chat;

import io.bot.inputs.ConsoleInput;
import io.bot.inputs.Input;
import io.bot.inputs.ValidateInput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * ChatBot.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/10/2019
 */
public class ChatBot {
    /**
     * field input.
     */
    private final Input in;
    /**
     * field file bot.
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
     * @param aBotTxt file bot
     * @param aLogTxt file log
     */
    public ChatBot(final Input aInput,
                   final String aBotTxt, final String aLogTxt) {
        this.botTxt = aBotTxt;
        this.logTxt = aLogTxt;
        this.in = aInput;
    }

    /**
     * Method run chat with bot.
     */
    public final void chat() {
        try (BufferedWriter writerLog =
                     new BufferedWriter(new FileWriter(this.logTxt))) {
            final String dataBot = getDataBotFile(this.botTxt);
            String bot;
            String user;
            var is = false;
            writerLog.write(".");
            while ((user = this.in.askUser()) != null) {
                switch (user) {
                    case "stop":
                        is = true;
                        writerLog.write(user + ".");
                        break;
                    case "continue":
                        is = false;
                        writerLog.write(user + ".");
                        bot = getBotAnswer(dataBot);
                        writerLog.write(bot + ".");
                        System.out.println(bot);
                        break;
                    case "end":
                        writerLog.write("end" + ".");
                        return;
                    default:
                        if (is) {
                            writerLog.write(user + ".");
                            break;
                        } else {
                            writerLog.write(user + ".");
                            bot = getBotAnswer(dataBot);
                            writerLog.write(bot + ".");
                            System.out.println(bot);
                            break;
                        }
                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method parse bot file and get random answer.
     *
     * @param dataBotFile data string from bot file
     * @return random answer after parse string dataBotFile
     */
    private String getBotAnswer(final String dataBotFile) {
        String[] split = dataBotFile.split("\\.");
        int rnd = new Random().nextInt(split.length);
        return split[rnd];
    }

    /**
     * Method open buffer and file reader and read bot file to string.
     *
     * @param botFile bot file
     * @return string data bot file
     *
     * @throws IOException exception
     */
    private String getDataBotFile(final String botFile) throws IOException {
        BufferedReader readerBot = new BufferedReader(new FileReader(botFile));
        StringBuilder bufferBot = new StringBuilder();
        while (readerBot.read() != -1) {
            bufferBot.append(readerBot.readLine());
        }
        readerBot.close();
        return bufferBot.toString();
    }

    /**
     * Method get path bot file.
     *
     * @return path bot file
     */
    public static String getPathBot() {
        return "C:/projects/job4j/chapter_005/src/main/java/io/bot/source/"
                + "bot.txt";
    }

    /**
     * Method get path log file.
     *
     * @return path log file
     */
    public static String getPathLog() {
        return "C:/projects/job4j/chapter_005/src/main/java/io/bot/source/"
                + "log.txt";
    }

    /**
     * Point entry to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        new ChatBot(new ValidateInput(
                new ConsoleInput()), getPathBot(), getPathLog()).chat();
    }
}
