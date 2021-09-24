package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ticket {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean purchase;

    public Ticket(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }

    public boolean isPurchase() {
        return purchase;
    }
}
