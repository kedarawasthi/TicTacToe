package org.tictactoe.exceptions;

public class InvalidGameStateException extends RuntimeException{
    public InvalidGameStateException(String message) {
        super(message);
    }
}
