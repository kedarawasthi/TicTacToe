package org.tictactoe.controllers;

import org.tictactoe.exceptions.InvalidGameConstructionException;
import org.tictactoe.models.Game;
import org.tictactoe.models.GameStatus;
import org.tictactoe.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(List<Player> players) throws InvalidGameConstructionException {
        Game game=Game.getBuilder().setPlayers(players).build();
        return game;
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }

    public Player getCurrentPlayer(Game game){
        return game.getCurrentPlayer();
    }

    public void undo(Game game){
        game.undo();
    }
}
