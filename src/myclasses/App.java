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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();
    List<Reader> readers = new ArrayList<>();
    List<History> histories = new ArrayList<>();

    public App() {
    }
    
    public void run(){
        String repeat = "y";
        do{
            System.out.println("Выберите задачу: ");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            System.out.println("5: Выдать книгу");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Вернуть книгу");
            
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task) {
                case 0: 
                    repeat="q";
                    System.out.println("Программа закончена");
                    break;
                case 1: 
                    System.out.println("Добавление книги: ");
                    books.add(addBook());
                    break;
                case 2: 
                    System.out.println("Список книг: ");
                    for (int i = 0; i < books.size(); i++) {
                        if(books.get(i) != null){
                            System.out.println(books.get(i).toString());
                        }
                    }
                    break;
                case 3: 
                    System.out.println("Добавление читателя: ");
                    readers.add(addReader());
                    break;
                case 4: 
                    System.out.println("Список читателей: ");
                    for (int i = 0; i < readers.size(); i++) {
                        if(readers.get(i) != null){
                            System.out.println(readers.get(i).toString());
                        }
                    }
                    break;
                case 5: 
                    System.out.println("Выдача книги: ");
                    histories.add(addHistory());
                    break;
                case 6: 
                    printGivenBooks();
                    break;
                case 7: 
                    System.out.println("Вернуть книгу: ");
                    printGivenBooks();
                    System.out.print("Выберите возвращаемую книгу: ");
                    int historyNumber = scanner.nextInt(); scanner.nextLine();
                    Calendar c = new GregorianCalendar();
                    histories.get(historyNumber-1).setReturnDate(c.getTime());
                    break;
            }
        }while("y".equals(repeat));
    }
    private void printGivenBooks(){
        System.out.println("Список выданных книг: ");
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null && histories.get(i).getReturnDate() == null){
                System.out.printf("%d. Книгу: %s читает %s %s%n",
                        i+1,
                        histories.get(i).getBook().getCaption(),
                        histories.get(i).getReader().getFirstname(),
                        histories.get(i).getReader().getLastname()
                );

            }
        }
    }
    private History addHistory(){
        History history = new History();
        /**
         * 1. Вывести нумерованный список книг
         * 2. получить от пользователя номер книги: bookNumber
         * 3. Вывести список читателей
         * 4. получить от пользователя номер читателя: readerNumber
         * 5. В history инициировать поле book объектом, который лежит в
         *      массиве books[bookNumber-1].
         * 6. В history инициировать поле reader объектом, который лежит в 
         *      массиве readers[readerNumber-1].
         * 7. Получить текущую дату и положить ее в поле history.givenDate
         * 
         */
        System.out.println("Список книг: ");
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null){
                System.out.printf("%d. %s%n",i+1,books.get(i).toString());
            }
        }
        System.out.print("Введите номер книги: ");
        int bookNumber = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(bookNumber-1));
        System.out.println();
        System.out.println("Список читателей: ");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s%n",i+1,readers.get(i).toString());
            }
        }
        System.out.print("Введите номер читателя: ");
        int readerNumber = scanner.nextInt(); scanner.nextLine();
        history.setReader(readers.get(readerNumber-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        return history;
    }
    private Reader addReader(){
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите номер телефона читателя: ");
        reader.setPhone(scanner.nextLine());
        return reader;
    }
    private Book addBook(){
        Book book = new Book();
        System.out.print("Введите название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Введите год издания: ");
        book.setPublishedYear(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Сколько авторов у книги: ");
        int countAuthors=scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Добавление автора "+(i+1));
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setName(scanner.nextLine());
            System.out.print("Фамилия автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("Год рождения автора: ");
            author.setYear(scanner.nextInt());
            System.out.print("День рождения автора: ");
            author.setDay(scanner.nextInt());
            System.out.print("Месяц рождения автора: ");
            author.setMonth(scanner.nextInt());
            scanner.nextLine();
            authors.add(author);
        }
        book.setAuthor(authors);
        return book;
    }
    
}
    
    

