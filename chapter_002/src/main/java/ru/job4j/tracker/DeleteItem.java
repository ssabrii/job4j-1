package ru.job4j.tracker;

import ru.job4j.start.Input;

public class DeleteItem implements UserAction {
    private int keyholder;
    private String menu;
    private boolean check = false;
    private StringBuilder infotext = new StringBuilder();

    public DeleteItem(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID удаляемой заявки: ");
        this.check = tracker.delete(id);
    }

    @Override
    public String info() {
        if (!this.check) {
            this.infotext.append("Заявка не удалена. Уточните ID заявки.");
        } else {
            this.infotext.append("Заявка удалена.");
        }
        return this.infotext.toString();
    }
}
