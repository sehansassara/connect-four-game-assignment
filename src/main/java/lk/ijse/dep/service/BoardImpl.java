package lk.ijse.dep.service;

import java.util.Arrays;

public class BoardImpl implements Board{
private Piece[][]  pieces;
private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;

        pieces =  new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
               pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return  boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        //ewana collumn ele empty row thiyeda balanawa
        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existLegalMoves() { //board eka athule his kotu monawa hri thiyed blnawa empty thibbe nathoth game eka draw wenawa
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return  true;

                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) { //u ewanawa collumn ekak ae collumn eke palaweni empty row ekata assign kragannawa u ewana pata
    pieces[col][findNextAvailableSpot(col)]=move;

   /*     for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                pieces[col][i] = move;
            }
        } */
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        //kelin athata collam check karagannawa
      for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length-3; j++) {//out of bound eka yawanna
                if (pieces[i][j] != Piece.EMPTY && pieces[i][j] == pieces[i][j+1] && pieces[i][j] == pieces[i][j+2] && pieces[i][j] == pieces[i][j+3]) {
                    return new Winner(pieces[i][j],i,j,i,j+3);
                }
            }
        }

        for (int i = 0; i < pieces.length-3; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] != Piece.EMPTY && pieces[i][j] == pieces[i+1][j] && pieces[i][j] == pieces[i+2][j] && pieces[i][j] == pieces[i+3][j]) {
                    return new Winner(pieces[i][j],i,j,i+3,j);
                }
            }
        }
        return new Winner(Piece.EMPTY);

    }
}
