package io.manager.sockets;

import com.google.common.base.Joiner;
import io.manager.properties.Root;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SocketServerTest {
    final static String LN = System.lineSeparator();
    private final Root root = new Root();

    public void setPropertiesServer(final String demand, final String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream in = new ByteArrayInputStream(demand.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        SocketServer server = new SocketServer(socket);
        server.startServer();
        final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        StringBuilder sb = new StringBuilder();
        while (dis.available() > 0) {
            sb.append(dis.readUTF());
        }
        assertThat(sb.toString(), is(expected));
        in.close();
        out.close();
        bos.close();
        socket.close();
        System.setOut(System.out);
    }

    @Test
    public void whenServerExitOk() throws IOException {
        final var demand = "exit";
        final var expected = Joiner.on(LN).join("exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdSlashOk() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd /", "exit", "");
        final var expected = Joiner.on(LN).join(path, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdDotsRefuse() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd ..", "exit", "");
        final var expected = Joiner.on(LN).join("Refuse.Root.@" + path, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdDotsOk() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd deep", "cd ..", "exit", "");
        final var expected = Joiner.on(LN).join(path + "/deep", path, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdDirectoryOk() throws IOException {
        final var path = this.root.getRootCatalog() + "/deep";
        final var demand = Joiner.on(LN).join("cd deep", "exit", "");
        final var expected = Joiner.on(LN).join(path, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdDirectoryFallPath() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd test", "exit", "");
        final var lineExpected = "Refuse.Illegal path.@" + path;
        final var expected = Joiner.on(LN).join(lineExpected, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerCdDirectoryFallCommand() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd x5x5 ..", "exit", "");
        final var lineExpected = "Refuse.illegal command.@" + path;
        final var expected = Joiner.on(LN).join(lineExpected, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerDirOk() throws IOException {
        final var path = this.root.getRootCatalog();
        final var demand = Joiner.on(LN).join("cd sockets", "dir", "exit", "");
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream in = new ByteArrayInputStream(demand.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        SocketServer server = new SocketServer(socket);
        server.startServer();
        final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        StringBuilder sb = new StringBuilder();
        while (dis.available() > 0) {
            sb.append(dis.readUTF());
        }
        final var a = sb.toString().split(LN);
        final var b = a[1].split("@");
        a[1] = a[2];
        a[2] = null;
        final var result = new String[a.length - 1 + b.length];
        System.arraycopy(a, 0, result, 0, a.length - 1);
        System.arraycopy(b, 0, result, a.length - 1, b.length);
        final var pathSocket = path + "/sockets";
        final var fileOne = "package-info.java <file>";
        final var fileTwo = "SocketClient.java <file>";
        final var fileThree = "SocketServer.java <file>";
        final var exit = "exit";
        final String[] expected = {pathSocket, exit, fileOne, fileTwo, fileThree, path};
        assertThat(result, arrayContainingInAnyOrder(expected));
        in.close();
        out.close();
        bos.close();
        socket.close();
    }

    @Test
    public void whenServerLoadOk() throws IOException {
        final var path = this.root.getRootCatalog();
        final var queryLoad = "load dump.txt " + path + "/dump";
        final var demand = Joiner.on(LN).join("cd deep", queryLoad, "dump", "exit", "");
        final var lineExpected = "Load OK.@" + path + "/deep";
        final var expected = Joiner.on(LN).join(path + "/deep", lineExpected, "exit", "");
        setPropertiesServer(demand, expected);
    }

    @Test
    public void whenServerUnLoadOk() throws IOException {
        final var path = this.root.getRootCatalog();
        final var queryLoad = "unload deep.txt " + path + "/dump";
        final var demand = Joiner.on(LN).join("cd deep", queryLoad, "exit", "");
        final var lineExpected = "UnLoad OK.@" + path + "/deep";
        final var expected = Joiner.on(LN).join(path + "/deep", lineExpected, "exit", "");
        setPropertiesServer(demand, expected);
    }

}
