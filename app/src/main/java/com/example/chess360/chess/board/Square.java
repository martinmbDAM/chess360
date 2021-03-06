package com.example.chess360.chess.board;

import com.example.chess360.chess.pieces.*;

public class Square {

    private final int row;
    private final int column;
    private final String name;
    private Piece piece;

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getName() {
        return this.name;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece miPiece){
        this.piece = miPiece;
    }

    public Square(int row, int column){
        this.row = row;
        this.column = column;
        this.name = this.translateCoordinates(row, column);
        this.piece = null;
    }

    public Square(Square square){
        this.row = square.getRow();
        this.column = square.getColumn();
        this.name = square.getName();

        if (square.getPiece() == null){
            this.piece = null;
        }
        else{

            Piece myPiece = square.getPiece();

            if (myPiece instanceof Pawn){
                this.piece = new Pawn((Pawn)myPiece);
            }
            else if (myPiece instanceof Rook){
                this.piece = new Rook((Rook)myPiece);
            }
            else if (myPiece instanceof Knight){
                this.piece = new Knight((Knight)myPiece);
            }
            else if (myPiece instanceof Bishop){
                this.piece = new Bishop((Bishop)myPiece);
            }
            else if (myPiece instanceof Queen){
                this.piece = new Queen((Queen)myPiece);
            }
            else if (myPiece instanceof King){
                this.piece = new King((King)myPiece);
            }
        }

    }

    public Square(String myName){
        int [] coordinates = this.translateName(myName);
        this.row = coordinates[0];
        this.column = coordinates[1];
        this.name = myName;
        this.piece = null;
    }

    // Translates coordinates to a name in algebraic notation:
    public static String translateCoordinates(int x, int y) {

        String myName;

        int r = x+1;
        char c = (char) ('a' + y);

        myName = Character.toString(c) + Integer.toString(r);

        return (myName);
    }

    // Translates a name in algebraic notation to coordinates:
    public static int [] translateName(String name){

        int [] output = new int [2];

        output[0] = Integer.parseInt(Character.toString(name.charAt(1)))-1;
        output[1] = name.charAt(0) - 'a';

        return output;
    }

    @Override
    public boolean equals(Object object){

        boolean output = false;

        if (object instanceof Square){

            Square mySquare = (Square) object;
            output = this.row == mySquare.getRow() &&
                    this.column == mySquare.getColumn() &&
                    this.name.equals(mySquare.getName()) &&
                    this.piece.equals(mySquare.getPiece());
        }

        return output;
    }

    // Removes a piece from a square:
    public void emptySquare(){
        this.piece = null;
    }

    public boolean isEmpty(){
        return this.piece == null;
    }
}
