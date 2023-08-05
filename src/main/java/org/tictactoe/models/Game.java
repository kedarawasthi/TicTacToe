package org.tictactoe.models;

import javafx.util.Pair;
import org.tictactoe.exceptions.InvalidGameConstructionException;
import org.tictactoe.strategies.check_winning.OrderOneWinningStrategy;
import org.tictactoe.strategies.check_winning.PlayerWonStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private GameStatus gameStatus;
    private List<Move> moves;
    private PlayerWonStrategy playerWonStrategy;

    private Game(GameBuilder gameBuilder) {
        this.board = gameBuilder.board;
        this.players = gameBuilder.players;
        this.currentPlayerIndex = gameBuilder.currentPlayerIndex;
        this.gameStatus = gameBuilder.gameStatus;
        this.moves = gameBuilder.moves;
        this.playerWonStrategy=gameBuilder.playerWonStrategy;
    }

    //Builder method
    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    //Extra Functions
    public void makeMove() {
        /*0th: get current player
        * 1st: take row,col as input from user for next move
        * 2nd: validate the cell
        * 3rd: if cell is okay: make the move
        *       else keep on poking user to enter valid move,then make the move
        * 4th: Check for win or draw
        * 5th: if there is win or draw, update the status accordingly and return
        *      else increment current player index
        * */
        //step 0,1
        Player currentPlayer=players.get(currentPlayerIndex);
        Pair<Integer,Integer> nextMove=currentPlayer.getNextMove(board); //takes input from the player, should be handled inside player class ? why taking input from model class, to save time in interview, explain to interviewer

        //step2,3
        while(!validateMove(nextMove)){
            System.out.println("Not a valid move ,Please choose another cell");
            nextMove=currentPlayer.getNextMove(board);
        }
        Cell cell=board.getCell(nextMove.getKey(),nextMove.getValue()); // gets you the cell from board
        cell.setPlayer(currentPlayer); // setPlayer should also set status of cell to occupied
        Move move = new Move(cell, currentPlayer);
        moves.add(move);

        //step 4,5
        if(checkForWin(move)){
            gameStatus=GameStatus.ENDED;
            return;
        }

        if(checkForDraw()){
            gameStatus=GameStatus.DRAW;
            return;
        }

        currentPlayerIndex = (currentPlayerIndex + 1 )%players.size();
    }

    private boolean validateMove(Pair<Integer,Integer> nextMove){
        int row=nextMove.getKey();
        int col=nextMove.getValue();
        boolean isValidCell= row>=0 && col>=0 && row< board.getSize() && col< board.getSize();
        if(!isValidCell){
            return false;
        }
        Cell cell=board.getCell(row,col);
        return cell.getCellStatus().equals(CellStatus.AVAILABLE);
    }
    private boolean checkForDraw() {
        return moves.size() == (board.getSize() * board.getSize());
    }

    private boolean checkForWin(Move move) {
        return playerWonStrategy.hasWon(move);
    }

    public void displayBoard(){
        board.displayBoard(); //should be handled by board class
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
    //Getters and Setters
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public static class GameBuilder{
        private Board board;
        private List<Player> players;
        private int currentPlayerIndex;
        private GameStatus gameStatus;
        private List<Move> moves;
        private PlayerWonStrategy playerWonStrategy;

        public Game build() throws InvalidGameConstructionException{
            if(players == null || players.size()==0){
                throw new InvalidGameConstructionException("Players cant be null or empty"); //has to be complie time exception so that user is forced to handle this at compile time,
            }
            this.board=new Board(players.size()+1);
            this.currentPlayerIndex=0;
            this.gameStatus=GameStatus.IN_PROGRESS;
            this.moves=new ArrayList<>();
            this.playerWonStrategy=new OrderOneWinningStrategy(board.getSize());
            return new Game(this);
        }

        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setCurrentPlayerIndex(int currentPlayerIndex) {
            this.currentPlayerIndex = currentPlayerIndex;
            return this;
        }

        public GameBuilder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public GameBuilder setMoves(List<Move> moves) {
            this.moves = moves;
            return this;
        }
    }

}
