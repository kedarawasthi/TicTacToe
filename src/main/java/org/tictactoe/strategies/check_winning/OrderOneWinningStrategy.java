package org.tictactoe.strategies.check_winning;

import org.tictactoe.models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneWinningStrategy implements PlayerWonStrategy {

    List<Map<Character,Integer>> rows;
    List<Map<Character,Integer>> cols;
    Map<Character,Integer> diagonols;
    Map<Character,Integer> reverseDiagonols;

    public OrderOneWinningStrategy(int n) {
        rows=new ArrayList<>();
        cols=new ArrayList<>();
        diagonols=new HashMap<>();
        reverseDiagonols=new HashMap<>();
        for(int i=0;i<n;i++) {
            rows.add(new HashMap<>());
            cols.add(new HashMap<>());
        }
    }

    @Override
    public boolean hasWon(Move move) {
        char symbol=move.getPlayer().getSymbol();
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        int n=rows.size();

        Map<Character,Integer> tempMap= rows.get(row);
        tempMap.put(symbol,tempMap.getOrDefault(symbol,0) + 1);

        tempMap= cols.get(col);
        tempMap.put(symbol,tempMap.getOrDefault(symbol,0) + 1);

        boolean cellExistsOnDiagonols = ( row == col );
        boolean cellExistsOnReverseDiagonols = ( row + col == n-1 );

        if(cellExistsOnDiagonols){
            diagonols.put(symbol, diagonols.getOrDefault(symbol,0) + 1);
        }
        if(cellExistsOnReverseDiagonols){
            reverseDiagonols.put(symbol, reverseDiagonols.getOrDefault(symbol,0) + 1);
        }

        boolean wonOnRow = rows.get(row).get(symbol) == n;
        boolean wonOnCol = cols.get(col).get(symbol) == n;
        boolean wonOnDiagonols = cellExistsOnDiagonols && (diagonols.get(symbol) == n);
        boolean wonOnReverseDiagonols = cellExistsOnReverseDiagonols && (reverseDiagonols.get(symbol) == n);

        return wonOnRow || wonOnCol || wonOnDiagonols || wonOnReverseDiagonols;
    }


}
