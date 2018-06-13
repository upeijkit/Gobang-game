package edu.kit.informatik.quarto;

/**
 * a player choosing or placing a piece.
 * 
 * @author baikai
 * @version 1.0
 */
public class Player {
    private final int id;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "P" + String.valueOf(this.id);
    }

}
