package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Borrowing")
public class Borrowing implements Serializable {
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "returned")
    private boolean returned;

    @Id
    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    public Borrowing() {

    }

    public Borrowing(LocalDate startDate, LocalDate endDate, Client client, Book book) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.book = book;
        this.returned = false;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setReturned(){
        this.returned = true;
    }

    boolean getReturned(){
        return returned;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(client, borrowing.client) &&
                Objects.equals(book, borrowing.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(client, book);
    }
}
