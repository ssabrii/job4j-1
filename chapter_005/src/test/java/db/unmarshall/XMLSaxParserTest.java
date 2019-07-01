package db.unmarshall;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class XMLSaxParserTest {
    SAXParserFactory factory = SAXParserFactory.newInstance();

    @Test
    public void whenSAXParserXMLFile() throws ParserConfigurationException, SAXException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final var saxParser = this.factory.newSAXParser();
        final var handler = new XMLSaxParser();
        final var file = new File(
                Objects.requireNonNull(XMLSaxParser.class.getClassLoader().getResource("bank.xml")).getFile()
        );
        saxParser.parse(file, handler);
        System.setOut(new PrintStream(bos));
        handler.readList();
        assertThat(bos.toString(), is(new StringBuilder()
                        .append("Accounts in bank '2'.")
                        .append(System.lineSeparator())
                        .append("Account Details - Type:'current', Name:'Apple', Balance:'10', Id:'1001'.")
                        .append(System.lineSeparator())
                        .append("Account Details - Type:'current', Name:'Oracle', Balance:'20', Id:'1002'.")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
        System.setOut(System.out);
    }

    @Test
    public void whenSAXParserXMLFileAddedBalance() throws ParserConfigurationException, SAXException, IOException {
        final var saxParser = this.factory.newSAXParser();
        final var handler = new XMLSaxParser();
        final var file = new File(
                Objects.requireNonNull(XMLSaxParser.class.getClassLoader().getResource("bank.xml")).getFile()
        );
        saxParser.parse(file, handler);
        final ArrayList<Account> accList = handler.getAccounts();
        final var result = accList.get(0).getBalance() + accList.get(1).getBalance();
        assertThat(result, is(30));
    }

}
