package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

public class AddItem implements UserAction {
    private int keyholder;
    private String menu;
    private Item item;
    private boolean check = false;
    private StringBuilder infotext = new StringBuilder();

    public AddItem(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 0;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------- Добавление новой заявки -----------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        this.item = new Item(name, desc);
        this.check = tracker.add(this.item) != null;
    }

    @Override
    public String info() {
        if (!this.check) {
            infotext.append("Заявка не добавлена. Хранилище полное.");
            infotext.append(System.lineSeparator());
        }else{
            infotext.append("----- Новая заявка с ID: ");
            infotext.append(this.item.getId());
            infotext.append("----");
            infotext.append(System.lineSeparator());
        }
        return this.infotext.toString();
    }
}