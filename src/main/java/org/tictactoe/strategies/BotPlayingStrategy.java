package org.tictactoe.strategies;

import javafx.util.Pair;
import org.tictactoe.models.Board;

public interface BotPlayingStrategy {
    Pair<Integer,Integer> getNextMove(Board board);
}
