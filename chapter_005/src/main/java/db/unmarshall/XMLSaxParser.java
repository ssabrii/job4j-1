package db.unmarshall;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * XMLSaxParser.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/1/2019
 */
public class XMLSaxParser extends DefaultHandler {
    /**
     * field account.
     */
    private Account account;
    /**
     * field temp string.
     */
    private String temp;
    /**
     * field the list of account.
     */
    private final ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Getter list.
     *
     * @return the list of account
     */
    public final ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public final void characters(final char[] buffer,
                                 final int start,
                                 final int length) {
        this.temp = new String(buffer, start, length);
    }


    @Override
    public final void startElement(final String uri,
                                   final String localName,
                                   final String qName,
                                   final Attributes attributes) {
        this.temp = "";
        if (qName.equalsIgnoreCase("Account")) {
            this.account = new Account();
            this.account.setType(attributes.getValue("type"));
        }
    }

    @Override
    public final void endElement(final String uri,
                                 final String localName,
                                 final String qName) {
        if (qName.equalsIgnoreCase("Account")) {
            this.accounts.add(this.account);
        } else if (qName.equalsIgnoreCase("Name")) {
            this.account.setName(this.temp);
        } else if (qName.equalsIgnoreCase("Id")) {
            this.account.setId(Integer.parseInt(this.temp));
        } else if (qName.equalsIgnoreCase("Balance")) {
            this.account.setBalance(Integer.parseInt(this.temp));
        }
    }

    /**
     * Method print data accounts.
     */
    protected final void readList() {
        System.out.println("Accounts in bank '" + this.accounts.size() + "'.");
        for (final Account curAccount : this.accounts) {
            System.out.println(curAccount.toString());
        }
    }
}
