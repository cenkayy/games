package mode;

import alert.GameFinishedAlert;
import javafx.scene.control.Button;
import scene.Sorry;

public class GameModeImp implements GameMode {
	static char player = 'O', opponent = 'X';
	Button[] arr = new Button[9];

	public GameModeImp(Button[] arr) {
		for (int i = 0; i < arr.length; i++)
			this.arr[i] = arr[i];

	}

	@Override
	public boolean checkWinnerP1VSP2() {
		char[][] sections = new char[3][3];
		int x = 0;
		int y = 0;
		for (int i = 0; i < arr.length; i++) {
			if (y == 3) {
				y = 0;
				x++;
			}
			if (arr[i].getText().length() > 0)
				sections[x][y] = arr[i].getText().charAt(0);
			else
				sections[x][y] = 'Z';
			y++;
		}
		for (int row = 0; row < 3; row++) {
			if (sections[row][0] == sections[row][1] && sections[row][0] == sections[row][2]) {
				if (sections[row][0] != 'Z') {
					winnerText(sections[row][0]);
					for (int i = 0; i < 3; i++)
						arr[(row * 3) + i].setStyle("-fx-background-color:green");
					return true;
				}
			}
		}
		for (int col = 0; col < 3; col++) {
			if (sections[0][col] == sections[1][col] && sections[0][col] == sections[2][col]) {
				if (sections[0][col] != 'Z') {
					winnerText(sections[0][col]);
					for (int i = 0; i < 3; i++)
						arr[(i * 3) + col].setStyle("-fx-background-color:green");
					return true;
				}
			}
		}
		if (sections[0][0] == sections[1][1] && sections[0][0] == sections[2][2]) {
			if (sections[0][0] != 'Z') {
				winnerText(sections[0][0]);
				for (int i = 0; i < 3; i++)
					arr[(i * 3) + i].setStyle("-fx-background-color:green");
				return true;
			}
		}
		if (sections[0][2] == sections[1][1] && sections[0][2] == sections[2][0]) {
			if (sections[0][2] != 'Z') {
				winnerText(sections[0][2]);
				arr[2].setStyle("-fx-background-color:green");
				arr[4].setStyle("-fx-background-color:green");
				arr[6].setStyle("-fx-background-color:green");
				return true;
			}
		}
		return false;
	}

	@Override
	public void turnForAI() {
		char[][] sections = new char[3][3];
		int x = 0;
		int y = 0;
		for (int i = 0; i < arr.length; i++) {
			if (y == 3) {
				y = 0;
				x++;
			}
			if (arr[i].getText().length() > 0)
				sections[x][y] = arr[i].getText().charAt(0);
			else
				sections[x][y] = '_';
			y++;
		}

		Move bestMove = findBestMove(sections);
		int index = (bestMove.row * 3) + bestMove.col;
		if (Sorry.isFirstRandom()) {
			index = (int) (Math.random() * 8);
			Sorry.setFirstRandom(false);
		}
		arr[index].setText("O");
		arr[index].setStyle("-fx-background-color:orange");

	}

	private void winnerText(char charWin) {
		if (charWin == 'X')
			new GameFinishedAlert().finishAlert("Player 1 Wins!", arr);
		else if (charWin == 'O')
			if (Sorry.isAI())
				new GameFinishedAlert().finishAlert("Computer Wins!", arr);
			else
				new GameFinishedAlert().finishAlert("Player 2 Wins!", arr);
	}

	static Boolean isMovesLeft(char board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == '_')
					return true;
		return false;
	}

	static int evaluate(char b[][]) {
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == player)
					return +10;
				else if (b[row][0] == opponent)
					return -10;
			}
		}
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == player)
					return +10;
				else if (b[0][col] == opponent)
					return -10;
			}
		}

		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return +10;
			else if (b[0][0] == opponent)
				return -10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return +10;
			else if (b[0][2] == opponent)
				return -10;
		}

		return 0;
	}

	static int minimax(char board[][], int depth, Boolean isMax) {
		int score = evaluate(board);

		if (score == 10)
			return score;

		if (score == -10)
			return score;

		if (isMovesLeft(board) == false)
			return 0;

		if (isMax) {
			int best = -1000;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == '_') {
						board[i][j] = player;
						best = Math.max(best, minimax(board, depth + 1, !isMax));
						board[i][j] = '_';
					}
				}
			}
			return best;
		}

		else {
			int best = 1000;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {

					if (board[i][j] == '_') {
						board[i][j] = opponent;
						best = Math.min(best, minimax(board, depth + 1, !isMax));
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}

	static Move findBestMove(char board[][]) {
		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (board[i][j] == '_') {
					board[i][j] = player;
					int moveVal = minimax(board, 0, false);
					board[i][j] = '_';

					if (moveVal > bestVal) {
						bestMove.row = i;
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
			}
		}

		return bestMove;
	}

	static class Move {
		int row, col;
	};

}
