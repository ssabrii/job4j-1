package ru.job4j.tracker;

import ru.job4j.start.Input;

import java.util.Arrays;

public class ShowItems implements UserAction {
    private int keyholder;
    private String menu;

    public ShowItems(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println(Arrays.toString(tracker.findAll()));
    }

    @Override
    public String info() {
        return "All Items from Storage.";
    }
}
