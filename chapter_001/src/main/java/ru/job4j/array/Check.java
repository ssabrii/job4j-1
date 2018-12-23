package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (boolean index : data) {
            for (boolean runner : data) {
                if (index != runner) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
