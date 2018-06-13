package edu.kit.informatik.exception;

public class CellAccessException extends SemanticsException {
    public CellAccessException(int height, int width, int x, int y) {
        super("the cell with coords (" + x + "," + y + ") " + "does not exist in a " + height + "*" + width
                + " board.");
    }
}
