package org.tictactoe.models;

import java.util.*;

public class Board {
    private List<List<Cell>> cells;

    public Board(int n) {
        this.cells=new ArrayList<>(n);
        for(int i=0;i<n;i++){
            List<Cell> row=new ArrayList<>();
            for(int j=0;j<n;j++){
                row.add(new Cell(i,j));
            }
            cells.add(row);
        }
    }

    //Extra function
    public void displayBoard(){
        for(List<Cell> row: cells){
            for(Cell cell:row){
                if(cell.getCellStatus().equals(CellStatus.AVAILABLE)){
                    System.out.print(" _ ");
                }
                else{
                    System.out.print(" "+cell.getPlayer().getSymbol()+" ");
                }
            }
            System.out.println();
        }
    }
    public int getSize(){
        return cells.size();
    }
    public Cell getCell(int row,int col){
        return cells.get(row).get(col);
    }

    //Getters,Setters
    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
}
