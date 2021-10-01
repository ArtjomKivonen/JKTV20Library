/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

/**
 *
 * @author pupil
 */
public class App {

    public App() {
    }
    public void run() {
        //System.out.println("Hello");
        Book book1 = new Book();
        book1.setCaption("Voina i mir");
        Author author1 = new Author();
        author1.setName("Lev");
        author1.setLastname("Tolstoi");
        author1.setYear(1828);
        author1.setDay(9);
        author1.setMonth(9);
        Author[] authors = new Author[1];
        authors[0]=author1;
    }
}
