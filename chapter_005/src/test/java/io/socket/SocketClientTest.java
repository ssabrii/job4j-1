package io.socket;

import com.google.common.base.Joiner;
import io.socket.connect.SocketClient;
import io.socket.input.Input;
import io.socket.input.StubInput;
import io.socket.input.ValidateInput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SocketClientTest {
    public static final String LN = System.getProperty("line.separator");

    public final void setTestClient(final String[] request, final String result) throws IOException {
        final Input input;
        final Socket socket = mock(Socket.class);
        input = new ValidateInput(new StubInput(request));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        SocketClient client = new SocketClient(socket, input);
        client.startClient();
        assertThat(out.toString(), is(result));
        System.setOut(System.out);
    }

    @Test
    public void whenClientOutExit() throws IOException {
        final String[] query = {"exit"};
        final String result = Joiner.on(LN).join("exit", "");
        setTestClient(query, result);
    }


    @Test
    public void whenClientExit() throws IOException {
        final String[] query = {"Hello Oracle", "exit"};
        final String result = Joiner.on(LN).join("Hello Oracle", "exit", "");
        setTestClient(query, result);

    }


    @Test
    public void whenClientHelloThenBackGreatOracle() throws IOException {
        final String[] query = {"to be or not to be?", "exit"};
        final String result = Joiner.on(LN).join("to be or not to be?", "exit", "");
        setTestClient(query, result);
    }
}