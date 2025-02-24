package com.example.sudoku_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SudokuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuBackendApplication.class, args);
		SudokuService sudokuService = new SudokuService();

		// Generálj egy új Sudoku táblát
		Sudoku sudoku = sudokuService.generateSudokuTable();
		sudoku.printBoard(); // Nyomtasd ki a táblát

		System.out.println();

		// Oldd meg a Sudoku-t
		sudokuService.boardFilling(sudoku.getBoard());
		sudoku.printBoard(); // Nyomtasd ki a megoldott táblát
	}

}
