package edu.kit.informatik.quarto.scanner;

import edu.kit.informatik.quarto.QuartoGame;

public abstract class Scanner {
    private QuartoGame mGame;

    public Scanner(QuartoGame pGame) {
        this.mGame = pGame;
    }

    public abstract boolean hasWon();

    public QuartoGame getGame() {
        return this.mGame;
    }
}
