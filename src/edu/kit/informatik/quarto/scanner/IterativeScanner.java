package edu.kit.informatik.quarto.scanner;

import edu.kit.informatik.exception.CellAccessException;
import edu.kit.informatik.quarto.Cell;
import edu.kit.informatik.quarto.QuartoGame;
import edu.kit.informatik.quarto.TokenPropertyType;

public class IterativeScanner extends Scanner {
    public IterativeScanner(QuartoGame pGame) {
        super(pGame);
    }

    @Override
    public boolean hasWon() {
        for (int i = 0; i < getGame().getHeight(); i++) {
            for (int j = 0; j < getGame().getWidth(); j++) {
                if (checkForConnectFour(getDiagonalDown(i, j)) || checkForConnectFour(getDiagonalUp(i, j))
                        || checkForConnectFour(getVerticalDown(i, j)) || checkForConnectFour(getHorizotalRight(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkForConnectFour(Cell[] cells) {
        outer: for (TokenPropertyType cType : TokenPropertyType.values()) {
            Object expectedPropertyValue = null;
            for (int i = 0; i < cells.length; i++) {
                if (!cells[i].hasToken() || (!(expectedPropertyValue == null)
                        && !cells[i].getToken().getProperties().get(cType).getValue().equals(expectedPropertyValue))) {
                    continue outer;
                }
                expectedPropertyValue = cells[i].getToken().getProperties().get(cType).getValue();
            }
            return true;
        }
        return false;
    }

    private Cell[] getDiagonalDown(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < cells.length; i++) {
            try {
                cells[i] = getGame().getCell(x + i, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }
        return cells;
    }

    private Cell[] getDiagonalUp(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < cells.length; i++) {
            try {
                cells[i] = getGame().getCell(x - i, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }
        return cells;
    }

    private Cell[] getVerticalDown(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < cells.length; i++) {
            try {
                cells[i] = getGame().getCell(x - i, y);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }
        return cells;
    }

    private Cell[] getHorizotalRight(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < cells.length; i++) {
            try {
                cells[i] = getGame().getCell(x, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }
        return cells;
    }

}
