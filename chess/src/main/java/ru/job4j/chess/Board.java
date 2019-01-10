package ru.job4j.chess;

import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;

public class Board {
    private Figures figure;
    private final Figures[] figures = new Figures[32];
    private int index = 0;

    public void add(Figures figures) {
        this.figures[this.index++] = figures;
    }

    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        if (source == null) {
            throw new FigureNotFoundException();
        } else if (dest != null) {
            throw new ImpossibleMoveException();
        } else if (dest != null) {
            throw new OccupiedWayException();
        }
        return true;
    }

}
