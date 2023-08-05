package org.tictactoe.strategies.check_winning;

import org.tictactoe.models.Move;

public interface PlayerWonStrategy {
    public boolean hasWon(Move move);
}
