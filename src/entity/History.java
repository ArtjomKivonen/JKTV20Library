/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Melnikov
 */
public class History implements Serializable{
    private Book book;
    private Reader reader;
    private Date givenDate;
    private Date returnDate;

    public History() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "History{"
                + "\nbook=" + book.toString()
                + ",\nreader=" + reader.toString()
                + ",\n givenDate=" + givenDate.toString()
                + ",\n returnDate=" + returnDate
                + "\n}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.book);
        hash = 71 * hash + Objects.hashCode(this.reader);
        hash = 71 * hash + Objects.hashCode(this.givenDate);
        hash = 71 * hash + Objects.hashCode(this.returnDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.givenDate, other.givenDate)) {
            return false;
        }
        if (!Objects.equals(this.returnDate, other.returnDate)) {
            return false;
        }
        return true;
    }
    
}
