package edu.kit.informatik;

import edu.kit.informatik.exception.SemanticsException;
import edu.kit.informatik.exception.SyntaxException;
import edu.kit.informatik.game.Quarto;
import edu.kit.informatik.quarto.Bag;
import edu.kit.informatik.quarto.Player;
import edu.kit.informatik.quarto.QuartoGame;

public class MainClass {
    private static final int height = 4;
    private static final int width = 4;

    public MainClass() {

    }

    public static void main(String[] args) {
        if (args.length != 0) {
            Terminal.printLine("Error, no commmand-line arguments allowed.");
        }
        Player[] players = new Player[] {
                new Player(1),
                new Player(2)
        };
        Bag bag = new Bag();
        QuartoGame game = new QuartoGame(height, width, players, bag);
        try {
            Quarto.startInteractiveSequence(game);
        } catch (SyntaxException | SemanticsException e) {
            Terminal.printLine("Error, " + e.getMessage());

        }

    }
}
