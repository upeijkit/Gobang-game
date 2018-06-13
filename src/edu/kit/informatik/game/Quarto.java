package edu.kit.informatik.game;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exception.CellAccessException;
import edu.kit.informatik.exception.SemanticsException;
import edu.kit.informatik.exception.SyntaxException;
import edu.kit.informatik.quarto.QuartoGame;

public final class Quarto {
    private static final String SELECT = "select";
    private static final String PLACE = "place";
    private static final String BAG = "bag";
    private static final String ROWPRINT = "rowprint";
    private static final String COLPRINT = "colprint";
    private static final String QUIT = "quit";
    private static final String GAME = "game";

    private Quarto() {

    }

    public static void startInteractiveSequence(QuartoGame pGame) throws SyntaxException, SemanticsException {
        while (true) {
            String command = Terminal.readLine();
            if (command == null) {
                return;
            }
            try {
                String[] parts = command.split("\\s", 2);
                switch (parts[0]) {
                case SELECT:
                    select(pGame, parts[1]);
                    break;
                case PLACE:
                    place(pGame, parts[1]);
                    break;
                case BAG:
                    bag(pGame);
                    break;
                case ROWPRINT:
                    rowPrint(pGame, parts[1]);
                    break;
                case COLPRINT:
                    colPrint(pGame, parts[1]);
                    break;
                case QUIT:
                    return;
                case GAME:
                    Terminal.printLine(pGame.toString());
                    break;
                default:
                    if (command.length() == 0) {
                        throw new SyntaxException("please enter a command.");
                    } else {
                        throw new SyntaxException(
                                "command \"" + parts[0] + "\" not found. Only the following are valid: " + SELECT + ", "
                                        + PLACE + ", " + BAG + ", " + ROWPRINT + ", " + COLPRINT + ".");
                    }
                }
            } catch (SemanticsException | SyntaxException e) {
                Terminal.printLine("Error, " + e.getMessage());
            }
        }
    }

    public static void select(QuartoGame pGame, String param) throws SemanticsException, SyntaxException {
        if (pGame.isGameOver()) {
            throw new SemanticsException("cannot execute select command after the game is finished.");
        }
        try {
            int index = Integer.parseInt(param);
            pGame.selectToken(index);
            Terminal.printLine("OK");
        } catch (NumberFormatException e) {
            throw new SyntaxException("the token's index must be valid integer.");
        }
    }

    public static void place(QuartoGame pGame, String param) throws SemanticsException, SyntaxException {
        if (pGame.isGameOver()) {
            throw new SemanticsException("cannot execute place command after the gamen is finished.");
        }
        String[] parts = param.split(";", 2);
        if (parts.length < 2) {
            throw new SemanticsException("at least two semicolon seperated values required for place command.");
        }
        try {
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);
            pGame.placeToken(row, column);
            if (pGame.isGameOver()) {
                if (pGame.getWinner() != null) {
                    Terminal.printLine(pGame.getWinner().toString() + " wins");
                    Terminal.printLine(pGame.getMoveNumber());
                } else {
                    Terminal.printLine("draw");
                }
            } else {
                Terminal.printLine("OK");
            }
        } catch (NumberFormatException e) {
            throw new SyntaxException("row and column's index both must be valid integers.");
        }

    }

    public static String bag(QuartoGame pGame) {
        return pGame.getBagContent();

    }

    public static void rowPrint(QuartoGame pGame, String param) throws SemanticsException, CellAccessException {
        try {
            int row = Integer.parseInt(param);
            Terminal.printLine(pGame.getRowPrint(row));
        } catch (NumberFormatException e) {
            Terminal.printLine("the row index has to be valid interger.");
        }

    }

    public static void colPrint(QuartoGame pGame, String param) throws SemanticsException, CellAccessException {
        try {
            int column = Integer.parseInt(param);
            pGame.getColumnPrint(column);
        } catch (NumberFormatException e) {
            Terminal.printLine("the column index has to be valid integer.");
        }
    }

}
