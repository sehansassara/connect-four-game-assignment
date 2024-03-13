package lk.ijse.dep.service;

public class AiPlayer extends Player{
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        do {

                int range = (5 - 0) + 1;
                col = (int)(Math.random() * range) + 0;

        }while (!board.isLegalMove(col)); //collumn eka  pirenakal karanna kiyala wenne

        board.updateMove(col,Piece.GREEN);
        board.getBoardUI().update(col,false);

        if (board.findWinner().getWinningPiece().equals(Piece.EMPTY)) {
            if (!board.existLegalMoves()) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }else{
            board.getBoardUI().notifyWinner(board.findWinner());

        }

    }
}
