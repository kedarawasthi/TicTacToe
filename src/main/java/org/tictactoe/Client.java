package org.tictactoe;

import org.tictactoe.controllers.GameController;
import org.tictactoe.exceptions.InvalidGameConstructionException;
import org.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter number of human players");
        int num=scanner.nextInt();
        List<Player> players=new ArrayList<>();
        for(int i=0;i<num;i++){
            System.out.println("Enter the name of the player number "+(i+1));
            String name=scanner.next();
            System.out.println("Enter symbol for the player number "+(i+1));
            char symbol=scanner.next().charAt(0);
            Player player=new Player(name,symbol);
            players.add(player);
        }
        boolean singlePLayerFlag=players.size()==1;
        boolean inputForBotFlag=false;

        if(!singlePLayerFlag){
            System.out.println("Should we add a bot Y/N");
            String addBot = scanner.next();

            inputForBotFlag = addBot.equalsIgnoreCase("y")?true:false;
        }
       if(singlePLayerFlag || inputForBotFlag){
           players.add(new Bot("BOT-1",'B', BotLevel.LOW));
       }

        GameController gameController=new GameController();
        Game game;
        try{
            game=gameController.createGame(players);
        }
        catch(InvalidGameConstructionException e){
            System.out.println("Invalid Game Construction Exception "+ e.getMessage());
            return;
        }

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            gameController.displayBoard(game);
            gameController.makeMove(game);
        }

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){
            Player player=gameController.getCurrentPlayer(game);
            System.out.println(player.getName()+" has Won !!!");
        }

        if(gameController.getGameStatus(game).equals(GameStatus.DRAW)){
            Player player=gameController.getCurrentPlayer(game);
            System.out.println(" Game was draw");
        }

        System.out.println("Final Board");
        gameController.displayBoard(game);
    }
}
