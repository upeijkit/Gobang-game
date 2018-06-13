package edu.kit.informatik.quarto;

import edu.kit.informatik.exception.CellAccessException;
import edu.kit.informatik.exception.SemanticsException;
import edu.kit.informatik.quarto.scanner.IterativeScanner;
import edu.kit.informatik.quarto.scanner.Scanner;

/**
 * a game of quarto
 * 
 * @author baikai
 * @version 1.0
 */
public class QuartoGame {
    private final Bag mBag;
    private final Cell[][] mCells;
    private final int mHeight;
    private final int mWidth;
    private Player mWinner = null;
    private boolean mFinisched = false;
    private Token mCurrentSelectedToken = null;
    private int mCurrentActivePlayerIndex = 0;
    private Player[] mPlayers;
    private Scanner mScanner;
    private int mMoveNumber = -1;

    public QuartoGame(int height, int width, Player[] pPlayers, Bag pBag) {
        this.mHeight = height;
        this.mWidth = width;
        this.mCells = new Cell[height][width];
        this.mPlayers = pPlayers;
        this.mBag = pBag;
        this.mScanner = new IterativeScanner(this);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                this.mCells[row][column] = new Cell(row, column);
            }
        }
    }

    public Cell[][] getCells() {
        return this.mCells;
    }

    public Cell getCell(int x, int y) throws CellAccessException {
        if (x < 0 || x >= getHeight() || y < 0 || y >= getWidth()) {
            throw new CellAccessException(getHeight(), getWidth(), x, y);
        }
        return this.mCells[x][y];
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public Bag getBag() {
        return this.mBag;
    }

    public String getBagContent() {
        return getBag().toString();
    }

    public Player getWinner() {
        return this.mWinner;
    }

    public int getMoveNumber() {
        return this.mMoveNumber;
    }

    public Player getCurrentActivePlayer() {
        return this.mPlayers[this.mCurrentActivePlayerIndex];
    }

    public Token getCurrentSelectedToken() {
        return this.mCurrentSelectedToken;
    }

    public boolean isGameOver() {
        return this.mFinisched;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.mCells.length; i++) {
            String line = "";
            for (int j = 0; j < this.mCells[i].length; j++) {
                line += this.mCells[i][j].toString();
            }
            out += ((i > 0) ? "\n" : "") + line;
        }
        return out;
    }

    public void selectToken(Integer pIndex) throws SemanticsException {
        if (this.mCurrentSelectedToken != null) {
            throw new SemanticsException("the user has to place the selected token before selecting another token.");
        }
        this.mCurrentSelectedToken = mBag.getTokenByIndex(pIndex);
        this.mBag.removeToken(pIndex);
        changeCurrentActivePlayer();
    }

    public void placeToken(int row, int column) throws SemanticsException, CellAccessException {
        if (this.mCurrentSelectedToken == null) {
            throw new SemanticsException("you need to select a token first.");
        }
        Cell cCell = getCell(row, column);
        cCell.placeToken(this.mCurrentSelectedToken);
        this.mCurrentSelectedToken = null;
        if (this.mScanner.hasWon()) {
            this.mWinner = this.getCurrentActivePlayer();
            this.mFinisched = true;
        } else if (this.mBag.isEmpty()) {
            this.mFinisched = true;
        }
        this.mMoveNumber++;

    }

    public void changeCurrentActivePlayer() {
        changeCurrentActivePlayer(false);
    }

    public void changeCurrentActivePlayer(Boolean reverseDirection) {
        if (!reverseDirection || this.mCurrentActivePlayerIndex > 0) {
            this.mCurrentActivePlayerIndex = (this.mCurrentActivePlayerIndex + ((reverseDirection) ? -1 : 1))
                    % this.mPlayers.length;
        } else {
            this.mCurrentActivePlayerIndex = this.mPlayers.length - 1;
        }
    }

    public String getRowPrint(int row) throws SemanticsException, CellAccessException {
        if (row < 0 || row >= getHeight()) {
            throw new SemanticsException(
                    "the row index has to be between 0 and " + (getHeight() - 1) + "(both inclusive).");
        }
        String out = "";
        for (int i = 0; i < getWidth(); i++) {
            if (!out.isEmpty()) {
                out += " ";
            }
            try {
                out += getCell(row, i).toString();
            } catch (CellAccessException e) {

            }
        }
        return out;

    }

    public String getColumnPrint(int column) throws SemanticsException, CellAccessException {
        if (column < 0 || column >= getWidth()) {
            throw new SemanticsException(
                    "the column index has to be between 0 and" + (getWidth() - 1) + "both inlusive.");
        }
        String out = "";
        for (int j = 0; j < this.mHeight; j++) {
            if (!out.isEmpty()) {
                out += " ";
            }
            try {

                out += getCell(j, column).toString();
            } catch (CellAccessException e) {

            }
        }
        return out;
    }

}
