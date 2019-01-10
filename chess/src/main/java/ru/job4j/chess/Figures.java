package ru.job4j.chess;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

public abstract class Figures {
    private final Cell position;

    public Figures(final Cell position) {
        this.position = position;
    }

    public abstract Cell position();

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figures copy(Cell dest);
}