package ru.job4j.converter;

/**
 * Currency converter.
 */
public class Converter {
    /**
     * Price 1 euro in rubles.
     */
    private final int eur = 70;
    /**
     * Price 1 dollar in rubles.
     */
    private final int usd = 60;

    /**
     * Exchange rubles to euros.
     *
     * @param value count rubles.
     * @return euro.
     */
    public final int rubleToEuro(final int value) {
        return value / eur;
    }

    /**
     * Exchange rubles to dollars.
     *
     * @param value count rubles.
     * @return Dollars.
     */
    public final int rubleToDollar(final int value) {
        return value / usd;
    }

    /**
     * Exchange dollars to rubles.
     *
     * @param value count dollars.
     * @return Rubles.
     */
    public final int dollarToRuble(final int value) {
        return value * usd;
    }

    /**
     * Exchange euros to rubles.
     *
     * @param value count euros.
     * @return Rubles.
     */
    public final int euroToRuble(final int value) {
        return value * eur;
    }

    /**
     * Main.
     * the point's enter in program.
     *
     * @param args array string.
     */
    public static void main(final String[] args) {

    }
}
