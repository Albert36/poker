package entity;

public class Opponent
{
    private String amazonID;
    private int card;
    private String decision;

    public double getDecide_time() {
        return decide_time;
    }

    public void setDecide_time(double decide_time) {
        this.decide_time = decide_time;
    }

    private double decide_time;

    public String getAmazonID() {
        return amazonID;
    }

    public void setAmazonID(String amazonID) {
        this.amazonID = amazonID;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
