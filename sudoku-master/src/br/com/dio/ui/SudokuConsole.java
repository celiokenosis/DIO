package br.com.dio.ui;

import java.util.Scanner;

import br.com.dio.model.SudokuBoard;
import br.com.dio.service.SudokuGenerator;
import br.com.dio.service.SudokuValidator;

public class SudokuConsole {

    private SudokuBoard board;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        board = new SudokuBoard(SudokuGenerator.generate());

        System.out.println("=== SUDOKU - JAVA CONSOLE ===");

        while (true) {
            printBoard();

            if (board.isComplete()) {
                System.out.println("🎉 Parabéns! Você venceu!");
                break;
            }

            System.out.println("Informe: linha coluna valor (1-9) | 0 para sair");
            System.out.print(">> ");

            int row = scanner.nextInt();
            if (row == 0) break;

            int col = scanner.nextInt();
            int value = scanner.nextInt();

            row--;
            col--;

            if (board.getCell(row, col).isFixed()) {
                System.out.println("❌ Célula fixa, não pode alterar!");
                continue;
            }

            if (value < 1 || value > 9) {
                System.out.println("❌ Valor inválido!");
                continue;
            }

            if (SudokuValidator.isValid(board.toMatrix(), row, col, value)) {
                board.getCell(row, col).setValue(value);
            } else {
                System.out.println("❌ Jogada inválida!");
            }
        }
    }

    private void printBoard() {
        System.out.println();
        System.out.println("  1 2 3   4 5 6   7 8 9");

        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0) {
                System.out.println("  -------------------");
            }

            System.out.print((r + 1) + "| ");

            for (int c = 0; c < 9; c++) {
                int value = board.getCell(r, c).getValue();
                System.out.print(value == 0 ? ". " : value + " ");

                if ((c + 1) % 3 == 0 && c < 8) {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  -------------------");
    }
}

