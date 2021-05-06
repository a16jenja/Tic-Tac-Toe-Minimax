package tic_tac_toe_minimax;

import java.util.Scanner;

public class Game {
	char gameBoard[][] = {{ '_', '_', '_' },
			  			  { '_', '_', '_' },
			  			  { '_', '_', '_' }};
	
	char ai = 'X';
	char human = 'O';
	char currentPlayersTurn = human;
	boolean gameRunning = true;
	int spotsOpen = 9;
	Scanner scan = new Scanner(System.in);
	boolean flag = true;
	
	public void pickAMove() {
		System.out.println("It is your turn! Choose where you wanna place your 'O' (1 to 9):");
		int input = scan.nextInt();
		boolean flag = true;
		while(flag) {
				if(gameBoard[(int)(Math.floor((input-1)/3))][(input-1)%3] != '_') {
					System.out.println("Invalid position! Try again!");
					printGameBoard();
					input = scan.nextInt();
				} else {
					flag = false;
				}
		}
		gameBoard[(int)(Math.floor((input-1)/3))][(input-1)%3] = 'O';
		printGameBoard();
		currentPlayersTurn = ai;
	}
	
	public void bestMove() {
		int bestScore = -1000;
		int[] bestMove = new int[2];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(gameBoard[i][j] == '_') {
					gameBoard[i][j] = ai;
					int score = minimax(gameBoard, 0, false);
					System.out.println(score);
					gameBoard[i][j] = '_';
					if(score > bestScore) {
						bestScore = score;
						bestMove[0] = i;
						bestMove[1] = j;
					}
				}
			}
		}
		gameBoard[bestMove[0]][bestMove[1]] = ai;
		printGameBoard();
		currentPlayersTurn = human;
	}
	
	public boolean checkIfEqual(char a, char b, char c) {
		  return a == b && b == c && a != '_';
	}
	
	public boolean areThereAnyMovesLeft(char board[][]) 
	{ 
	    for (int i = 0; i < 3; i++) 
	        for (int j = 0; j < 3; j++) 
	            if (board[i][j] == '_') 
	                return true; 
	    return false; 
	} 
	
	public int checkWinner() {
		
		// check if horizontal win
		for(int i = 0; i < 3; i++) {
			if(checkIfEqual(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2])) {
				if(gameBoard[i][0] == 'O') {
					return -10;
				}
				else if (gameBoard[i][0] == 'X') {
					return 10;
				}
			}
		}
		
		// check if vertical win
		for(int i = 0; i < 3; i++) {
			if(checkIfEqual(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i])) {
				if(gameBoard[0][i] == 'O') {
					return -10;
				}
				else if (gameBoard[0][i] == 'X') {
					return 10;
				}
			}
		}
		
		// check if diagonal win
		if(checkIfEqual(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2])) {
			if(gameBoard[0][0] == 'O') {
				return -10;
			}
			else if (gameBoard[0][0] == 'X') {
				return 10;
			}
		}
		if(checkIfEqual(gameBoard[2][0], gameBoard[1][1], gameBoard[0][2])) {
			if(gameBoard[0][2] == 'O') {
				return -10;
			}
			else if (gameBoard[0][2] == 'X') {
				return 10;
			}
		}
		
		return 0;
	}
	
	public int minimax(char[][] gameBoard, int depth, boolean isMaximizing) {
		int score = checkWinner();
		if(score == 10) {
			return score;
		}
		if(score == -10) {
			return score;
		}
		
		if (areThereAnyMovesLeft(gameBoard) == false) {
			return 0;
		}
		
		if(isMaximizing) {
			int bestScore = -1000;
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gameBoard[i][j] == '_') {
						gameBoard[i][j] = ai;
						bestScore = Math.max(bestScore, minimax(gameBoard, depth + 1, !isMaximizing));
						gameBoard[i][j] = '_';
					}
				}
			}
			return bestScore;
		} else {
			int bestScore = 1000;
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (gameBoard[i][j] == '_') {
						gameBoard[i][j] = human;
						bestScore = Math.min(bestScore, minimax(gameBoard, depth + 1, !isMaximizing));
						gameBoard[i][j] = '_';
					}
				}
			}
			return bestScore;
		}
	}
	
	public void printGameBoard() {
		System.out.println("---------------");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print("| ");
				System.out.print(gameBoard[i][j]);
				System.out.print(" |");
			}
			System.out.println();
			System.out.println("---------------");
		}
	}
	
	public char checkIfSomeoneWon() {
		// check if horizontal win
				for(int i = 0; i < 3; i++) {
					if(checkIfEqual(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2])) {
						if(gameBoard[i][0] == 'O') {
							return 'O';
						}
						else if (gameBoard[i][0] == 'X') {
							return 'X';
						}
					}
				}
				
				// check if vertical win
				for(int i = 0; i < 3; i++) {
					if(checkIfEqual(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i])) {
						if(gameBoard[0][i] == 'O') {
							return 'O';
						}
						else if (gameBoard[0][i] == 'X') {
							return 'X';
						}
					}
				}
				
				// check if diagonal win
				if(checkIfEqual(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2])) {
					if(gameBoard[0][0] == 'O') {
						return 'O';
					}
					else if (gameBoard[0][0] == 'X') {
						return 'X';
					}
				}
				if(checkIfEqual(gameBoard[2][0], gameBoard[1][1], gameBoard[0][2])) {
					if(gameBoard[0][2] == 'O') {
						return 'O';
					}
					else if (gameBoard[0][2] == 'X') {
						return 'X';
					}
				}
		return 0;
	}
	
	public void resetGameBoard() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = '_';
			}
		}
		spotsOpen = 9;
		currentPlayersTurn = human;
	}
	
	public void askIfPlayAgain() {
		System.out.println("Play again? (y/n)");
		char c = scan.next().charAt(0);
		System.out.println(c);
		if(c != 'y' && c != 'n') {
			System.out.println("Invalid command.");
			askIfPlayAgain();
		} else if(c == 'n') {
			System.out.println("Thank you for playing!");
			flag = false;
		} else if(c == 'y') {
			gameRunning = true;
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		
		while(game.flag) {
			game.resetGameBoard();
			while(game.gameRunning) {
				if(game.currentPlayersTurn == game.human) {
					game.pickAMove();
				} else if(game.currentPlayersTurn == game.ai) {
					game.bestMove();
				}
				
				game.spotsOpen--;
				
				if(game.spotsOpen == 0) {
					System.out.println("It's a tie!");
					game.gameRunning = false;
				}
				
				if(game.checkIfSomeoneWon() == 'O' || game.checkIfSomeoneWon() == 'X') {
					System.out.println(game.checkIfSomeoneWon() + " wins!");
					game.gameRunning = false;
				}
			}
			game.askIfPlayAgain();
		}
	}
}
