import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get board size
        System.out.print("Enter board size (3-10): ");
        int size = scanner.nextInt();

        if (size < 3 || size > 10) {
            System.out.println("Invalid board size.");
            return;
        }

        char[][] board = new char[size][size];

        // Initialize board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }

        char currentPlayer = 'X';
        int moves = 0;
        int maxMoves = size * size;

        // Game loop
        while (moves < maxMoves) {
            printBoard(board);

            System.out.println("Player " + currentPlayer + ", enter row and column (0-based): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Validate move
            if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != '-') {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Place move
            board[row][col] = currentPlayer;
            moves++;

            // Check win
            if (checkWin(board, size, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }

            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        // Draw
        printBoard(board);
        System.out.println("It's a draw!");
    }

    // Print board
    public static void printBoard(char[][] board) {
        System.out.println("\nBoard:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Check win condition
    public static boolean checkWin(char[][] board, int size, char player) {

        // Check rows
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check columns
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                if (board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check main diagonal
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check anti-diagonal
        win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != player) {
                win = false;
                break;
            }
        }
        return win;
    }
}
