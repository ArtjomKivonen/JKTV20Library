/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Reader;
import entity.Book;
import entity.Author;
import entity.History;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Melnikov
 */
public class App {

    public App() {
    }
    
    public void run(){
        //System.out.println("Hello");
        Book book1 = new Book();
        book1.setCaption("Voina i Mir");
        Author author1 = new Author();
        author1.setName("Lev");
        author1.setLastname("Tolstoy");
        author1.setYear(1828);
        author1.setDay(9);
        author1.setMonth(9);
        Author[] authors = new Author[1];
        authors[0]=author1;
        book1.setAuthor(authors);
        book1.setPublishedYear(2005);
        Book book2 = new Book();
        book2.setCaption("Otsi i deti");
        Author author2 = new Author();
        author2.setName("Ivan");
        author2.setLastname("Turgenev");
        author2.setDay(9);
        author2.setMonth(11);
        author2.setYear(1818);
        Author[] authors2 = new Author[1];
        authors2[0]=author2;
        book2.setAuthor(authors2);
        book2.setPublishedYear(2007);
        
        Reader reader1 = new Reader();
        reader1.setFirstname("Ivan");
        reader1.setLastname("Ivanov");
        reader1.setPhone("4564545345");
        
        History history1 = new History();
        history1.setBook(book1);
        history1.setReader(reader1);
        Calendar c = new GregorianCalendar();
        history1.setGivenDate(c.getTime());
        System.out.println("history1 = "+history1.toString());
        System.out.println(" --------------- ");
        history1.setReturnDate(c.getTime());
        System.out.println("history1 = "+history1.toString());
        
    }
    
    
    
}
