package db.marshall;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

/**
 * XmlUsage.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/30/2019
 */
public final class XmlUsage {
    /**
     * Constructor.
     */
    private XmlUsage() {
    }

    /**
     * inner class User.
     */
    @XmlRootElement
    public static class User {
        /**
         * field values.
         */
        private List<Field> values;

        /**
         * Constructor.
         */
        @SuppressWarnings("unused")
        public User() {
        }

        /**
         * Constructor.
         *
         * @param aValues values
         */
        public User(final List<Field> aValues) {
            this.values = aValues;
        }

        /**
         * Getter.
         *
         * @return values
         */
        @SuppressWarnings("unused")
        public final List<Field> getValues() {
            return values;
        }

        /**
         * Setter.
         *
         * @param aValues values.
         */
        @SuppressWarnings("unused")
        public final void setValues(final List<Field> aValues) {
            this.values = aValues;
        }
    }

    /**
     * inner class Field.
     */
    @XmlRootElement
    public static class Field {
        /**
         * field values.
         */
        private int valueField;

        /**
         * Constructor.
         */
        @SuppressWarnings("unused")
        public Field() {
        }

        /**
         * Constructor.
         *
         * @param aValue valueField
         */
        public Field(final int aValue) {
            this.valueField = aValue;
        }

        /**
         * Getter.
         *
         * @return valueField
         */
        @SuppressWarnings("unused")
        public final int getValueField() {
            return valueField;
        }

        /**
         * Setter.
         *
         * @param aValue valueField
         */
        @SuppressWarnings("unused")
        public final void setValueField(final int aValue) {
            this.valueField = aValue;
        }
    }

    /**
     * Method point to program.
     *
     * @param args args
     * @throws Exception exception
     */
    public static void main(final String[] args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new User(Arrays.asList(new Field(1), new Field(2))),
                System.out
        );
    }
}
