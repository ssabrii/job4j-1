package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

public class FindItemById implements UserAction {
    private int keyholder;
    private String menu;
    private boolean check = false;
    private StringBuilder infotext = new StringBuilder();
    private Item byId;

    public FindItemById(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 4;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Поиск, введите ID заявки: ");
        this.byId = tracker.findById(id);
        this.check = byId != null;
    }

    @Override
    public String info() {
        if (!this.check) {
            infotext.append("Заявка не обнаружена. Уточните ID");
            infotext.append(System.lineSeparator());
        } else {
            infotext.append(this.byId);
            infotext.append(System.lineSeparator());
        }
        return this.infotext.toString();
    }
}
