package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;
/**
 * UpdateItem.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class UpdateItem implements UserAction {
    /**
     * The hold key.
     */
    private int keyholder;
    /**
     * The string menu on the hold key.
     */
    private String menu;
    private boolean check = false;
    private StringBuilder infotext = new StringBuilder();
    /**
     * Constructor.
     *
     * @param keyholder the hold key.
     * @param menu      the string menu on the hold key.
     */
    public UpdateItem(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }
    /**
     * Override method get the hold key.
     *
     * @return the hold key.
     */
    @Override
    public int key() {
        return 2;
    }
    /**
     * Override method updates Item.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID обновляемой заявки: ");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        this.check = tracker.replace(id, new Item(name, desc));
    }
    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public String info() {
        if (!this.check) {
            this.infotext.append("Заявка не обновлена.");
            this.infotext.append(System.lineSeparator());
        }else {
            this.infotext.append("Заявка обновлена.");
            this.infotext.append(System.lineSeparator());
        }
        return this.infotext.toString();
    }
}
