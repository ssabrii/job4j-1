package io.manager.sockets;

import com.google.common.base.Joiner;
import io.manager.input.Input;
import io.manager.input.InputStub;
import io.manager.input.InputValidate;
import io.manager.properties.Root;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SocketClientTest {
    final static String LN = System.lineSeparator();
    private final Root root = new Root();

    public void setPropertiesTest(final String demand, final String expected) throws IOException {
        final Socket socket = mock(Socket.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        final Input input = new InputValidate(new InputStub(demand.split(LN)));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(demand);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        SocketClient client = new SocketClient(socket, input);
        client.startClient();
        final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        StringBuilder sb = new StringBuilder();
        while (dis.available() > 0) {
            sb.append(dis.readUTF());
        }
        assertThat(sb.toString(), is(expected));
        dis.close();
        in.close();
        out.close();
        os.close();
        socket.close();
        System.setOut(System.out);
    }

    @Test
    public void whenFallCommandOK() throws IOException {
        final var demand = Joiner.on(LN).join("c", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "refuse", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenExitOK() throws IOException {
        final var demand = Joiner.on(LN).join("exit", "");
        final var expected = Joiner.on(LN).join("cd /", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenExitFallIllegalCommand() throws IOException {
        final var demand = Joiner.on(LN).join("exit da", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "refuse", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenDirOK() throws IOException {
        final var demand = Joiner.on(LN).join("dir", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "dir", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenCdSlashOK() throws IOException {
        final var demand = Joiner.on(LN).join("cd /", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "cd /", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenCdDirectoryOK() throws IOException {
        final var demand = Joiner.on(LN).join("cd deep", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "cd deep", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenCdTwoDotsOK() throws IOException {
        final var demand = Joiner.on(LN).join("cd ..", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "cd ..", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenCdFallByIllegalPath() throws IOException {
        final var demand = Joiner.on(LN).join("cd .. ..", "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "refuse", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenLoadOK() throws IOException {
        final var path = this.root.getRootCatalog();
        final var query = "load dump.txt " + path + "/dump";
        final var demand = Joiner.on(LN).join("cd deep", query, "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "cd deep", query, "dump", "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenUnLoadOK() throws IOException {
        final var path = this.root.getRootCatalog();
        final var query = "unload deep.txt " + path + "/dump";
        final var demand = Joiner.on(LN).join("cd deep", query, "exit", "");
        final var expected = Joiner.on(LN).join("cd /", "cd deep", query, "exit", "");
        setPropertiesTest(demand, expected);
    }

    @Test
    public void whenAnnounce() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        final var aInput = new InputValidate(new InputStub(new String[]{"exit"}));
        SocketClient client = new SocketClient(socket, aInput);
        client.startClient();
        final var ln = System.lineSeparator();
        assertThat(bos.toString(), is(new StringBuilder()
                        .append(" Use command:")
                        .append(ln)
                        .append(" \"cd /\"(root),")
                        .append(" \"cd ..\"(up),")
                        .append(" \"cd filename\"(down),")
                        .append(" \"dir\" (show dir)")
                        .append(ln)
                        .append(" \"load filename pathClient\" (load to current server catalog)")
                        .append(ln)
                        .append(" \"unload filename pathClient\" (unload to catalog client)")
                        .append(ln)
                        .append(ln)
                        .append(ln)
                        .toString()
                )
        );
        System.setOut(System.out);
    }
}
