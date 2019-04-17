package io.socket;

import com.google.common.base.Joiner;
import io.socket.connect.SocketServer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SocketServerTest {
    public static final String LN = System.getProperty("line.separator");

    public void testServer(final String input, final String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        SocketServer server = new SocketServer(socket);
        server.startServer();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenClientAnswerThenChooseRandom() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenClientHelloThenBackGreatOracle() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "Hello Oracle",
                        "exit"
                ),
                String.format("Hello, dear friend, I'm a Oracle.%s", LN)
        );
    }

    @Test
    public void whenClientAnyThenBackDontUnderstand() throws IOException {
        this.testServer(
                Joiner.on(LN).join("be or not to be?", "exit"),
                Joiner.on(LN).join("I don't understand.", ""));
    }
}