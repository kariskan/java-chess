import chess.Board;
import chess.ChessGame;
import chess.Command;
import chess.CommandType;
import chess.Ranks;
import java.util.Objects;
import view.InputView;
import view.OutputView;

public class ChessApplication {

	public static void main(String[] args) {
		Command command;
		ChessGame chessGame = null;
		do {
			command = InputView.inputCommand();
			try {
				chessGame = executeChessGame(command, chessGame);
			} catch (IllegalArgumentException e) {
				OutputView.printString(e.getMessage() + " please retry");
			}
		} while (command.getCommandType() != CommandType.END && !Objects.requireNonNull(chessGame).isGameEnd());
	}

	private static ChessGame executeChessGame(Command command, ChessGame chessGame) {
		if (command.getCommandType() == CommandType.START) {
			chessGame = new ChessGame(new Board(new Ranks()));
			OutputView.print(chessGame.board());
		} else if (command.getCommandType() == CommandType.MOVE) {
			chessGame.move(command.getArguments());
		}
		return chessGame;
	}
}
