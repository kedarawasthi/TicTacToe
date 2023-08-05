package org.tictactoe.models;

public class Cell {
    private int row;
    private int col;

    private CellStatus cellStatus;
    private Player player; // added to get players on specific cell,could have been done with move also
    public Cell (int row,int col){
        this.row=row;
        this.col=col;
        this.cellStatus=CellStatus.AVAILABLE;
    }



    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {

        this.player = player;
        this.cellStatus=CellStatus.OCCUPIED;
    }
    public void removePlayer() {

        this.player = null;
        this.cellStatus=CellStatus.AVAILABLE;
    }
}
