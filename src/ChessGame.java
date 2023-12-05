import java.util.Scanner;

public class ChessGame {
    private static char[][] board;

    public static void main(String[] args) {
        initializeMainMenu();
    }

    private static void initializeBoard() {
        board = new char[8][8];
        // Initialize pieces
        board[0] = "rnbqkbnr".toCharArray();
        board[1] = "pppppppp".toCharArray();
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }
        board[6] = "PPPPPPPP".toCharArray();
        board[7] = "RNBQKBNR".toCharArray();
    }

    private static void displayBoard() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(8 - i);
        }
        System.out.println("  a b c d e f g h");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Player 1's turn (White)");
            makeMove('W');

            displayBoard();
            if (isGameOver()) {
                System.out.println("Player 1 (White) wins!");
                break;
            }

            System.out.println("Player 2's turn (Black)");
            makeMove('B');

            displayBoard();
            if (isGameOver()) {
                System.out.println("Player 2 (Black) wins!");
                break;
            }
        }

        scanner.close();
        
    }

    private static void makeMove(char player) {

        Scanner scanner = new Scanner(System.in);
        int startX, startY, endX, endY;

        while (true) {
            System.out.print("Enter the start position (e.g., a2): ");
            String start = scanner.next();
            startY = start.charAt(0) - 'a';
            startX = '8' - start.charAt(1);

            if (isValidPosition(startX, startY) && isPlayerPiece(startX, startY, player)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }

            while (true) {
                System.out.println("Player 1's turn (White)");
                makeMove('W');
    
                displayBoard();
                if (isGameOver()) {
                    System.out.println("Player 1 (White) wins!");
                    break;
                }
    
                System.out.println("Player 2's turn (Black)");
                makeMove('B');
    
                displayBoard();
                if (isGameOver()) {
                    System.out.println("Player 2 (Black) wins!");
                    break;
                }
            }
    
            scanner.close();
        }

        while (true) {
            System.out.print("Enter the end position (e.g., a4): ");
            String end = scanner.next();
            endY = end.charAt(0) - 'a';
            endX = '8' - end.charAt(1);

            if (isValidPosition(endX, endY) && isValidMove(startX, startY, endX, endY, player)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        board[endX][endY] = board[startX][startY];
        board[startX][startY] = '.';
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private static boolean isPlayerPiece(int x, int y, char player) {
        char piece = board[x][y];
        return (player == 'W' && Character.isUpperCase(piece)) || (player == 'B' && Character.isLowerCase(piece));
    }

    private static boolean isValidMove(int startX, int startY, int endX, int endY, char player) {
        // Add specific rules for each chess piece if needed
        // For simplicity, assume any move is valid for now
        return true;
    }

    private static boolean isGameOver() {
        // Add game over conditions if needed
        return false;
    }

    // main menu
    private static void initializeMainMenu() {
        System.out.println("Welcome to Team 4's Chess Game");
        System.out.println("------------------------------");
        System.out.println("Press 1 to view the rules.");
        System.out.println("Press 2 to change the settings.");
        System.out.println("Press 3 to start the game.");
        System.out.println("------------------------------");

        Scanner scanner = new Scanner(System.in);

        char userInput = scanner.next().charAt(0);

        if (userInput =='1') {
            initializeRules();
        } else if(userInput =='2') {
            initializeSettings();
        } else if(userInput =='3') {
            initializeBoard();
            displayBoard();
        }

        scanner.close();
    }

    // settings menu
    private static void initializeSettings() {
        System.out.println("------------------------------");
        System.out.println("Settings - press 1 to exit:");
        System.out.println("------------------------------");
        System.out.println("No settings yet");
        System.out.println("------------------------------");

        Scanner scanner = new Scanner(System.in);

        char userInput = scanner.next().charAt(0);

        if (userInput =='1') {
            initializeMainMenu();
        }


    }

    // rules menu, rules modified from https://www.chess.com/learn-how-to-play-chess
    private static void initializeRules() {
        System.out.println("------------------------------");
        System.out.println("Rules - press 1 to exit");
        System.out.println("------------------------------");

        System.out.println("Moving Pieces:");
        System.out.println("1. The king can move one square in any direction: up, down, to the sides, and diagonally.");
        System.out.println("2. The queen can move in one straight direction as far as possible without moving through any of her own pieces.");
        System.out.println("3. The rook can move forward, backwards, and to the sides as far as it wants.");
        System.out.println("4. The bishop can move diagonally as far as it wants. It must stay on the color it started on.");
        System.out.println("5. The knight can move in an L shape 2 squares out and one over in the direction you choose.");
        System.out.println("6. The pawn can move forward one square at a time except for its first move where it can move 2 squares. Pawns can only capture one square diagonally in front of them.");
        System.out.println("----------");

        System.out.println("Special Rules:");
        System.out.println("1. Promoting a pawn occurs when the pawn reaches the other side of the board. You can choose for it to become any other piece.");
        System.out.println("2. En Passant occurs when a pawn moves out 2 squares on its first move and lands to the side of an opponents pawn. The opponent can capture your pawn if is passed in this way.");
        System.out.println("3. Castling: this allows the king to get to safety. It must be the kings first move, it must be the rooks first move, there cannot be any pieces between the king and the rook, and the king may not be in check or pass through check.");
        System.out.println("----------");
        Scanner scanner = new Scanner(System.in);

        char userInput = scanner.next().charAt(0);

        if (userInput =='1') {
            initializeMainMenu();
        }

    }
}
