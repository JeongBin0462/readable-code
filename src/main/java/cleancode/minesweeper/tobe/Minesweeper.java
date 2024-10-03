package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitialize;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class Minesweeper implements GameInitialize, GameRunnable {
    private final GameBoard gameBoard;

    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public Minesweeper(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
        gameBoard = new GameBoard(gameLevel);
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments();

        while (true) {
            try {
                outputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    outputHandler.showGameClearMessage();
                    break;
                }

                if (doesUserLoseTheGame()) {
                    outputHandler.showGameOverMessage();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getCellActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (AppException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.showExceptionMessage(e);
            }
        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();

            return;
        }

        if (!doesUserChooseToOpenCell(userActionInput)) {
            throw new AppException("잘못된 번호를 선택하였습니다.");
        }

        if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
            gameBoard.open(selectedRowIndex, selectedColIndex);
            changeGameStatusToLose();

            return;
        }

        gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
        checkIfGameIsOver();
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private String getCellActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        outputHandler.showCommentToGetCellInput();
        return inputHandler.getUserInput();
    }

    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }
}
