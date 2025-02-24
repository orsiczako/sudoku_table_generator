package com.example.sudoku_backend;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * A SudokuService osztályban fogjuk megvalósítani a Sudoku tábla generálását.
 * A cél egy érvényes és megoldott Sudoku tábla generálása.
 */
@Service
public class SudokuService {

    // Véletlenszám-generátor a Sudoku tábla számok elhelyezéséhez
    private final Random rand = new Random();

    /**
     * Generál egy teljes Sudoku táblát, ami érvényes megoldást tartalmaz.
     * A tábla generálásakor egy visszalépéses algoritmust használunk,
     * hogy megtaláljuk a megfelelő számokat a tábla minden mezőjére.
     *
     * @return egy érvényes és teljes Sudoku tábla.
     */
    public Sudoku generateSudokuTable() {
        Sudoku sudoku = new Sudoku(); // Új Sudoku objektum létrehozása
        int[][] board = sudoku.getBoard(); // Sudoku tábla (2D tömb)
        boardFilling(board); // Tábla generálása

        // Véletlenszerűen eltávolítunk számokat a tábláról
        removeValues(board, 40); // Eltávolítunk 40 értéket, vagy tetszőlegesen más számot
        return sudoku;
    }
    /**
     * Eltávolít véletlenszerűen számokat a tábláról.
     *
     * @param board a Sudoku tábla (2D tömb), amelyből el kell távolítani a számokat.
     * @param count a számok száma, amit el szeretnénk távolítani.
     */
    private void removeValues(int[][] board, int count) {
        int removed = 0;
        while (removed < count) {
            int row = rand.nextInt(9); // Véletlenszerű sor
            int col = rand.nextInt(9); // Véletlenszerű oszlop

            // Ha a mező már üres, folytatjuk
            if (board[row][col] != 0) {
                board[row][col] = 0; // Eltávolítjuk a számot
                removed++; // Növeljük az eltávolított számok számát
            }
        }
    }



    /**
     * Visszalépéses algoritmus segítségével feltölti a Sudoku táblát érvényes számokkal.
     * Ha egy számot nem tudunk elhelyezni, visszalépünk és próbálkozunk a következő lehetőséggel.
     *
     * @param board a Sudoku tábla (2D tömb), amelyet feltöltünk.
     * @return true, ha sikerült minden mezőt érvényes számokkal kitölteni, false ha nem.
     */
    public boolean boardFilling(int[][] board) {
        for (int sor = 0; sor < 9; sor++) {
            for (int oszlop = 0; oszlop < 9; oszlop++) {
                // Ha a mező üres (0), próbálunk számot elhelyezni
                if (board[sor][oszlop] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (validator(board, sor, oszlop, num)) { // Ha a szám érvényes
                            board[sor][oszlop] = num; // Szám elhelyezése
                            if (boardFilling(board)) { // Rekurzív hívás
                                return true;
                            }
                            board[sor][oszlop] = 0; // Ha nem sikerült, visszalépünk
                        }
                    }
                    return false; // Ha nem találtunk érvényes számot, visszalépünk
                }
            }
        }
        return true; // Minden mező kitöltése sikerült
    }

    /**
     * Ellenőrzi, hogy az adott szám érvényes-e a Sudoku szabályai szerint.
     * Egy szám akkor érvényes, ha nem szerepel az adott sorban, oszlopban és 3x3-as blokkban.
     *
     * @param board a Sudoku tábla.
     * @param sor a sor, ahol a számot szeretnénk elhelyezni.
     * @param oszlop az oszlop, ahol a számot szeretnénk elhelyezni.
     * @param num a szám, amelyet próbálunk elhelyezni.
     * @return true, ha a szám érvényes, false ha nem.
     */
    private boolean validator(int[][] board, int sor, int oszlop, int num) {
        // Ellenőrzi az adott sorban
        for (int i = 0; i < 9; i++) {
            if (board[sor][i] == num) {
                return false; // A szám már szerepel a sorban
            }
        }

        // Ellenőrzi az adott oszlopban
        for (int i = 0; i < 9; i++) {
            if (board[i][oszlop] == num) {
                return false; // A szám már szerepel az oszlopban
            }
        }

        // Ellenőrzi a 3x3-as blokkot
        int startRow = sor - sor % 3;
        int startCol = oszlop - oszlop % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false; // A szám már szerepel a blokkban
                }
            }
        }

        return true; // A szám érvényes
    }
}
