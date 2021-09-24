package cinema.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@Component
public class Cinema {
    private int total_rows;
    private int total_columns;

    @JsonInclude()
    private Seat[] available_seats;

    public Cinema() {
        this.total_rows = 9;
        this.total_columns = 9;
        available_seats = new Seat[total_rows * total_columns];
        initSeats();
    }

    private void initSeats() {
        int row = 1;
        int column = 1;
        int price = 10;
        for (int i = 0; i < total_rows * total_columns ; i++) {
            if (row > 4) {
                price = 8;
            }
            available_seats[i] = new Seat(row, column, price);
            column++;
            if (column > 9) {
                column = 1;
                row++;
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public Seat[] getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Seat[] available_seats) {
        this.available_seats = available_seats;
    }
}
