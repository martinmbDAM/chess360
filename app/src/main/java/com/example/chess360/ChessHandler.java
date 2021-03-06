package com.example.chess360;

import com.example.chess360.chess.Chess;
import com.example.chess360.chess.Move;

import java.util.ArrayList;

public class ChessHandler {

    private PlayZone playzone;
    private Chess chess;
    private Move promotionMove;

    public ChessHandler(PlayZone myPlayZone){
        this.playzone = myPlayZone;
        this.chess = new Chess(this);
        this.promotionMove = null;
    }

    public void initializeBoard(PlayZone myPlayZone, Chess myChess){

        this.playzone = myPlayZone;
        this.chess = myChess;

        String [][] initialPosition = this.chess.exportPosition();
        //   this.playzone.setPosition(initialPosition);
        //   this.playzone.setApertura(this.chess.getApertura());
    }

    public void getMove(String origin, String destination){

        boolean isValid = this.chess.importMove(origin, destination);

        if (isValid){

            String [][] position = this.chess.exportPosition();
            this.playzone.setPosition(position);




            if (this.chess.isInCheck(this.chess.isWhiteTurn(),this.chess.getBoard())){

                this.playzone.highlightRed(this.chess.getKing(this.chess.getBoard()).getName());
            }





            //     this.playzone.setApertura(this.chess.getApertura());
        }
    }

    public ArrayList<String> requestAvailableSquares(String square){
        return this.chess.getAvailableSquares(square);
    }

    public boolean isOccupied(String square){
        return this.chess.isOccupied(square);
    }

    public boolean isPlayerTurn(String square){
        return this.chess.isPlayerTurn(square);
    }

    public void reset(){
        this.chess = new Chess(this);
        this.initializeBoard(playzone, chess);
    }

    public String getFEN(){
        return this.chess.exportFEN();
    }

    public void requestPromotion(Move myMove){

        this.playzone.launchPromotionDialog();
        this.promotionMove = myMove;
    }

    public void getPromotionPiece(int option){

        switch(option){
            case Chess.PROMOTION_QUEEN:
                this.chess.promotePiece(this.promotionMove, Chess.PROMOTION_QUEEN);
                break;
            case Chess.PROMOTION_ROOK:
                this.chess.promotePiece(this.promotionMove, Chess.PROMOTION_ROOK);
                break;
            case Chess.PROMOTION_KNIGHT:
                this.chess.promotePiece(this.promotionMove, Chess.PROMOTION_KNIGHT);
                break;
            case Chess.PROMOTION_BISHOP:
                this.chess.promotePiece(this.promotionMove, Chess.PROMOTION_BISHOP);
                break;
        }

        this.promotionMove = null;

        String [][] position = this.chess.exportPosition();
        this.playzone.setPosition(position);
    }

    public void isInCheck(){
        if (this.chess.isInCheck(this.chess.isWhiteTurn(),this.chess.getBoard())){

            this.playzone.highlightRed(this.chess.getKing(this.chess.getBoard()).getName());
        }
    }

    public String [] getPlayersNames(){

        String [] names = new String[2];
        names[0] = this.chess.getWhitePlayer().getName();
        names[1] = this.chess.getBlackPlayer().getName();

        return names;
    }

    public int checkPlayersStatus(){

        int currentState =this.chess.checkPlayersStatus();
        int output = -1;

        switch(currentState){
            case Chess.WHITE_WINS_CHECKMATE:
                output = PlayZone.WHITE_WINS_CHECKMATE;
                break;
            case Chess.BLACK_WINS_CHECKMATE:
                output = PlayZone.BLACK_WINS_CHECKMATE;
                break;
       //     case Chess.WHITE_STALEMATE:
       //     case Chess.BLACK_STALEMATE:
         //       output = PlayZone.STALEMATE;
           //     break;
            default:
                output = Chess.PLAYING;
                break;
        }

        return output;
    }
}