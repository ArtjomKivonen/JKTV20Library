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
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import keeper.BaseKeeper;
import keeper.FileKeeper;

/**
 *
 * @author Melnikov
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();
    List<Reader> readers = new ArrayList<>();
    List<History> histories = new ArrayList<>();
    //Keeping keeper = new FileKeeper();
    Keeping keeper = new BaseKeeper();

    public App() {
        books = keeper.loadBooks();
        //readers = keeper.loadReaders();
        //histories = keeper.loadHistories();
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
            
            int task = getNumber();
            switch (task) {
                case 0: 
                    repeat="q";
                    System.out.println("Программа закончена");
                    break;
                case 1: 
                    addBook();
                    break;
                case 2: 
                    printListBooks();
                    break;
                case 3: 
                    addReader();
                    break;
                case 4: 
                    printListReaders();
                    break;
                case 5: 
                    addHistory();
                    break;
                case 6: 
                    printGivenBooks();
                    break;
                case 7: 
                    returnBook();
                    break;
                default:
                    System.out.println("Выберите номер из списка задач");
                    break;
            }
        }while("y".equals(repeat));
    }
    private Set<Integer> printGivenBooks(){
        System.out.println("Список выданных книг: ");
        Set<Integer> setNumberGivenBooks = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            //если history не null и книга не возварщена и книг в наличии меньше
            // чем записано в quantity -
            // печатаем книгу
            if(histories.get(i) != null 
                    && histories.get(i).getReturnDate() == null
                    && histories.get(i).getBook().getCount()
                        <histories.get(i).getBook().getQuantity()
                    ){
                System.out.printf("%d. Книгу: %s читает %s %s%n",
                        i+1,
                        histories.get(i).getBook().getCaption(),
                        histories.get(i).getReader().getFirstname(),
                        histories.get(i).getReader().getLastname()
                );
                setNumberGivenBooks.add(i+1);
            }
        }
        if(setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
    }
    private void addHistory(){
        System.out.println("Выдача книги: ");
        if(quit()) return;
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
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);

        history.setBook(books.get(bookNumber-1));
        System.out.println();
        System.out.println("Список читателей: ");
        Set<Integer> setNumbersReaders =  printListReaders();
        int readerNumber = insertNumber(setNumbersReaders);

        history.setReader(readers.get(readerNumber-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        books.get(bookNumber-1).setCount(history.getBook().getCount() - 1);
        keeper.saveBooks(books);
        histories.add(history);
        keeper.saveHistories(histories);
    }
    private void addReader(){
        System.out.println("Добавление читателя: ");
        if(quit()) return;
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите номер телефона читателя: ");
        reader.setPhone(scanner.nextLine());
        readers.add(reader);
        keeper.saveReaders(readers);
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    private void addBook(){
        System.out.println("Добавление книги: ");
        if(quit()) return;
        Book book = new Book();
        System.out.print("Введите название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Введите год издания: ");
        book.setPublishedYear(getNumber());
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        System.out.print("Сколько авторов у книги: ");
        int countAuthors=getNumber();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Добавление автора "+(i+1));
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setName(scanner.nextLine());
            System.out.print("Фамилия автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("Год рождения автора: ");
            author.setYear(getNumber());
            System.out.print("День рождения автора: ");
            author.setDay(getNumber());
            System.out.print("Месяц рождения автора: ");
            author.setMonth(getNumber());
            authors.add(author);
        }
        book.setAuthor(authors);
        books.add(book);
        keeper.saveBooks(books);
    }

    private Set<Integer> printListBooks() {
        System.out.println("Список книг: ");
        books = keeper.loadBooks();
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder cbAutors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                cbAutors.append(books.get(i).getAuthor().get(j).getName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getLastname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s. %s В наличии экземпряров: %d%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s Нет наличии. Будет возварщена: %s%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    private String getReturnDate(Book book){
        
        for (int i = 0; i < histories.size(); i++) {
            if(book.getCaption().equals(histories.get(i).getBook().getCaption())){
                LocalDate localGivenDate = histories.get(i).getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return "";
    }
    private Set<Integer> printListReaders() {
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей: ");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s%n"
                        ,i+1
                        ,readers.get(i).toString()
                );
                setNumbersReaders.add(i+1);
            }
        }
        return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("Вернуть книгу: ");
        if(quit()) return;
        Set<Integer> numbersGivenBooks = printGivenBooks();
        if(numbersGivenBooks.isEmpty()){
            return;
        }
        int historyNumber = insertNumber(numbersGivenBooks);
        Calendar c = new GregorianCalendar();
        histories.get(historyNumber - 1).setReturnDate(c.getTime());
        // Здесь объясняется что значит передача по ссылке в Java
        // https://coderoad.ru/40480/%D0%AD%D1%82%D0%BE-Java-pass-by-reference-%D0%B8%D0%BB%D0%B8-pass-by-value
        // Постарайтесь понять
        for (int i = 0; i < books.size(); i++) {
          if(books.get(i).getCaption().equals(histories.get(historyNumber-1).getBook().getCaption())){
            books.get(i).setCount(books.get(i).getCount()+1);
          }
        }
        keeper.saveBooks(books);
        keeper.saveHistories(histories);
    }

    private int getNumber() {
        do{
            try {
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }
    
}
    
    

