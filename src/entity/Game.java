package entity;

public class Game {
    private int player1_card;
    private int player2_card;
    private String player1_decision;
    private String player2_decision;

    public int getPlayer1_card() {
        return player1_card;
    }

    public void setPlayer1_card(int player1_card) {
        this.player1_card = player1_card;
    }

    public int getPlayer2_card() {
        return player2_card;
    }

    public void setPlayer2_card(int player2_card) {
        this.player2_card = player2_card;
    }

    public String getPlayer1_decision() {
        return player1_decision;
    }

    public void setPlayer1_decision(String player1_decision) {
        this.player1_decision = player1_decision;
    }

    public String getPlayer2_decision() {
        return player2_decision;
    }

    public void setPlayer2_decision(String player2_decision) {
        this.player2_decision = player2_decision;
    }

    public double getPlayer1_time() {
        return player1_time;
    }

    public void setPlayer1_time(double player1_time) {
        this.player1_time = player1_time;
    }

    public double getPlayer2_time() {
        return player2_time;
    }

    public void setPlayer2_time(double player2_time) {
        this.player2_time = player2_time;
    }

    private double player1_time;
    private double player2_time;

}
