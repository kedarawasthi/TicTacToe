package org.tictactoe.factories;

import org.tictactoe.models.BotLevel;
import org.tictactoe.strategies.BotPlayingStrategy;
import org.tictactoe.strategies.EasyBotPlayingStartegy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategy(BotLevel botLevel){
        switch(botLevel){
            case LOW:
            case HIGH:
            case MEDIUM:
                return new EasyBotPlayingStartegy();
        }
        throw new UnsupportedOperationException("Bot type " + botLevel +" is not supported");
    }
}
