package com.example.sudoku_backend;



import java.util.Arrays;
/**
 * A Sudoku osztályban fogjuk eltárolni a létrejött Sudoku táblát, ami 9x9-es lesz, tehát itt
 * főleg csak a szerkezetét alkotjuk meg, a generálást a SudokuService osztályban fogjuk megvalósítani
 */
public class Sudoku{
    /**
     * A board változóban tároljuk el a táblát
     */
    private int[][] board;
    /**
     * A Sudoku osztály konstruktora, ahol inicializáljuk a táblát
     */
    public Sudoku() {
        this.board = new int[9][9];
    }
    /**Getter a tábla lekéréséhez
     * */
    public int[][] getBoard() {
        return board;
    }
    /** Setter, ami beállítja a tábla értékét
    * @param board a beállítandó tábla
    */
    public void setBoard(int[][] board) {
        this.board = board;
    }
    /**Kiírja a táblát
     * */
    public void printBoard(){
        for(int i=0;i<9;i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }
}