package org.tictactoe.models;

import javafx.util.Pair;

import java.util.Scanner;

public class HumanPlayer extends Player implements UndoMove{
    public HumanPlayer(String name, char symbol) {
        super(name,symbol);
    }
    @Override
    public Pair<Integer,Integer> getNextMove(Board board){
        System.out.println(getName() + "'s turn ,please enter row and col");
        Scanner scanner=new Scanner(System.in);
        int row=scanner.nextInt();
        int col=scanner.nextInt();
        return new Pair<>(row,col);
    }
    @Override
    public boolean undo(){
        System.out.println("Do you want to undo your last move?");
        Scanner scanner=new Scanner(System.in);
        char input=scanner.next().charAt(0);
        return (input == 'y' || input == 'Y') ? true : false;
    }
}
