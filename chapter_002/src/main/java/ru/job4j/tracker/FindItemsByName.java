package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

import java.util.Arrays;

public class FindItemsByName implements UserAction {
    private int keyholder;
    private String menu;
    private StringBuilder infotext = new StringBuilder();
    private Item[] byNames;

    public FindItemsByName(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Поиск, введите название заявки:");
        this.byNames = tracker.findByName(name);
    }

    @Override
    public String info() {
        if (this.byNames.length == 0) {
            this.infotext.append("Заявка не обнаружена. Уточните название.");
            this.infotext.append(System.lineSeparator());
        } else {
            this.infotext.append(System.lineSeparator());
            this.infotext.append(Arrays.toString(this.byNames));
        }
        return this.infotext.toString();
    }
}
