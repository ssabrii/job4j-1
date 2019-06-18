package io.chat.menuchat;

import io.chat.inputs.Input;
import io.chat.inputs.StubInput;
import io.chat.inputs.ValidateInput;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChatTest {
    private Input input;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    private String bot;
    private String log;

    @Before
    public void addPathBotAndLog() {
        final var sp = File.separator;
        var property = System.getProperty("user.dir");
        if (!property.endsWith(sp)) {
            property += sp;
        }
        this.bot = property + "src/main/java/io/chat/source/bot.txt";
        this.log = property + "src/main/java/io/chat/source/log.txt";
    }

    @Before
    public void setOut() {
        System.setOut(new PrintStream(bos));
    }

    @Before
    public void unsetOut() {
        System.setOut(new PrintStream(out));
    }

    private String getLogFile() throws IOException {
        File newLog = new File(this.log);
        StringBuilder result = new StringBuilder();
        if (newLog.length() > 0) {

            BufferedReader reader = new BufferedReader(new FileReader(this.log));
            while (reader.read() != -1) {
                result.append(reader.readLine());
            }
            reader.close();
        }
        return result.toString();
    }

    @Test
    public void whenUserAnyStopContinue() throws IOException {
        this.input = new ValidateInput(
                new StubInput(
                        new String[]{"hi", "how?", "stop", "go", "go", "continue", "yes", "end"}));
        new Chat(this.input, this.bot, this.log).chat();
        String logFile = this.getLogFile();
        new String(bos.toByteArray());
        assertThat(logFile,
                is(new StringBuilder()
                        .append("hi")
                        .append(".BOT")
                        .append(".how?")
                        .append(".BOT")
                        .append(".stop")
                        .append(".go")
                        .append(".go")
                        .append(".continue")
                        .append(".BOT")
                        .append(".yes")
                        .append(".BOT")
                        .append(".end")
                        .toString()));

    }
    @Test
    public void whenUserStopContinue() throws IOException {
        this.input = new ValidateInput(
                new StubInput(
                        new String[]{"stop", "go", "go", "continue", "yes", "end"}));
        new Chat(this.input, this.bot, this.log).chat();
        String logFile = this.getLogFile();
        new String(bos.toByteArray());
        assertThat(logFile,
                is(new StringBuilder()
                        .append("stop")
                        .append(".go")
                        .append(".go")
                        .append(".continue")
                        .append(".BOT")
                        .append(".yes")
                        .append(".BOT")
                        .append(".end")
                        .toString()));

    }
    @Test
    public void whenUserEnd() throws IOException {
        this.input = new ValidateInput(
                new StubInput(
                        new String[]{"end"}));
        new Chat(this.input, this.bot, this.log).chat();
        String logFile = this.getLogFile();
        new String(bos.toByteArray());
        assertThat(logFile,
                is(new StringBuilder().append("end")
                        .toString()));
    }
}
