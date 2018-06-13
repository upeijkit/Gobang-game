package edu.kit.informatik.quarto;

import edu.kit.informatik.exception.SemanticsException;

/**
 * The cell of a Quarto game's board.
 * 
 * @author baikai
 * @version 1.0
 */
public class Cell {
    private final int mPosX;
    private final int mPosY;
    private Token mToken = null;

    public Cell(int pPosX, int pPosY) {
        this.mPosX = pPosX;
        this.mPosY = pPosY;
    }

    public int getPositionX() {
        return this.mPosX;
    }

    public int getPositionY() {
        return this.mPosY;
    }

    public Token getToken() {
        return this.mToken;
    }

    public void placeToken(Token pToken) throws SemanticsException {
        if (hasToken()) {
            throw new SemanticsException("cannot place token on a cell which holds token" + this.mToken + ".");
        }
        this.mToken = pToken;

    }

    public boolean hasToken() {
        return this.mToken != null;
    }

    @Override
    public String toString() {
        if (hasToken()) {
            return this.mToken.toString();
        } else {
            return "#";
        }
    }
}
