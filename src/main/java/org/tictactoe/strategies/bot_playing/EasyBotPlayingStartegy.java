package org.tictactoe.strategies.bot_playing;

import javafx.util.Pair;
import org.tictactoe.exceptions.InvalidGameStateException;
import org.tictactoe.models.Board;
import org.tictactoe.models.Cell;
import org.tictactoe.models.CellStatus;

import java.util.List;

public class EasyBotPlayingStartegy implements BotPlayingStrategy{
    @Override
    public Pair<Integer, Integer> getNextMove(Board board) {
        for(List<Cell> row:board.getCells()){
            for(Cell cell: row){
                if(cell.getCellStatus().equals(CellStatus.AVAILABLE)){
                    return new Pair<>(cell.getRow(),cell.getCol());
                }
            }
        }
        throw new InvalidGameStateException("No valid move left for bot");
    }
}
