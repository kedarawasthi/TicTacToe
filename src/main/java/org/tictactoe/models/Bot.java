package org.tictactoe.models;

import javafx.util.Pair;
import org.tictactoe.factories.BotPlayingStrategyFactory;
import org.tictactoe.strategies.bot_playing.BotPlayingStrategy;

public class Bot extends Player{
    private BotLevel botLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, char symbol,BotLevel botLevel) {
        super(name, symbol);
        this.botLevel=botLevel;
    }

    @Override
    public Pair<Integer, Integer> getNextMove(Board board) {
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategy(botLevel);
        return botPlayingStrategy.getNextMove(board);
    }


}
