package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.AppException;
import cleancode.minesweeper.tobe.GameBoard;

public interface OutputHandler {

    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameClearMessage();

    void showGameOverMessage();

    void showCommentToGetCellInput();

    void showCommentForUserAction();

    void showExceptionMessage(AppException e);

    void showExceptionMessage(Exception e);
}
