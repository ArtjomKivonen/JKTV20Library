/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Author;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Melnikov
 */
public class Book implements Serializable{
    private String caption;
    private List<Author> author;
    private int publishedYear;
    private int quantity;
    private int count;
  
    
    public Book() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "caption=" + caption 
                + ", author=" + Arrays.toString(author.toArray())
                + ", publishedYear=" + publishedYear 
                + ", quantity=" + quantity 
                + ", count=" + count + "eurocents"
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.caption);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + this.publishedYear;
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + this.count;
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
        final Book other = (Book) obj;
        if (this.publishedYear != other.publishedYear) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.caption, other.caption)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }
    
    
}
