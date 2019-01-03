package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

public class UpdateItem implements UserAction {
    private int keyholder;
    private String menu;
    private boolean check = false;
    private StringBuilder infotext = new StringBuilder();

    public UpdateItem(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID обновляемой заявки: ");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        this.check = tracker.replace(id, new Item(name, desc));
    }

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
