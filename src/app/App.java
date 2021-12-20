/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entity.Reader;
import entity.Book;
import entity.Author;
import entity.History;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
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
    public static boolean isBase;
    private Scanner scanner = new Scanner(System.in);
//    private List<Book> books = new ArrayList<>();
//    private List<Reader> readers = new ArrayList<>();
//    private List<History> histories = new ArrayList<>();
//    private List<Author> authors = new ArrayList<>();
    private BookFacade bookFacade = new BookFacade(Book.class);
    private ReaderFacade readerFacade = new ReaderFacade(Reader.class);
    private AuthorFacade  authorFacade = new AuthorFacade(Author.class);
    private HistoryFacade historyFacade = new HistoryFacade(History.class);


    public App() {
        
//        books = keeper.loadBooks();
//        authors = keeper.loadAuthors();
//        readers = keeper.loadReaders();
//        histories = keeper.loadHistories();
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
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Изменить книгу");
            
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
                case 8: 
                    addAuthor();
                    break;
                case 9: 
                    printListAuthors();
                    break;
                case 10: 
                    updateBook();
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
        List<History> historyWithGivenBooks = historyFacade.findHistoryWithGivenBooks();
        for (int i = 0; i < historyWithGivenBooks.size(); i++) {
            //если history не null и книга не возварщена и книг в наличии меньше
            // чем записано в quantity -
            // печатаем книгу
            if(historyWithGivenBooks.get(i) != null 
                    && historyWithGivenBooks.get(i).getReturnDate() == null
                    && historyWithGivenBooks.get(i).getBook().getCount()
                        <historyWithGivenBooks.get(i).getBook().getQuantity()
                    ){
                System.out.printf("%d. Книгу: %s читает %s %s%n",
                        historyWithGivenBooks.get(i).getId(),
                        historyWithGivenBooks.get(i).getBook().getCaption(),
                        historyWithGivenBooks.get(i).getReader().getFirstname(),
                        historyWithGivenBooks.get(i).getReader().getLastname()
                );
                setNumberGivenBooks.add(historyWithGivenBooks.get(i).getId().intValue());
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
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        history.setBook(bookFacade.find((long)bookNumber));
        Set<Integer> setNumbersReaders =  printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        int readerNumber = insertNumber(setNumbersReaders);

        history.setReader(readerFacade.find((long)readerNumber));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        Book book = bookFacade.find((long)bookNumber);
        book.setCount(history.getBook().getCount() - 1);
        bookFacade.edit(book);
        historyFacade.create(history);
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
        readerFacade.create(reader);
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
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            System.out.println("Добавьте автора.");
            return;
        }
        System.out.println("Если есть авторы книги в списке нажмите 1");
        if(getNumber() != 1){
            System.out.println("Добавьте автора.");
            return;
        }
        System.out.print("Сколько авторов у книги: ");
        Book book = new Book();
        List<Author> authorsBook=new ArrayList<>();
        int countAuthors=getNumber();
        for (int i = 0; i < countAuthors; i++) {
            System.out.print("Введите номер автора "+(i+1)+": ");
            int numberAuthor = insertNumber(setNumbersAuthors);
            authorsBook.add(authorFacade.find((long)numberAuthor));
        }
        book.setAuthor(authorsBook);
        System.out.print("Введите название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Введите год издания: ");
        book.setPublishedYear(getNumber());
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        bookFacade.create(book);

    }

    private Set<Integer> printListBooks() {
        System.out.println("Список книг: ");
        List<Book> books = bookFacade.findAll();
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
                        ,books.get(i).getId()
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s Нет наличии. Будет возварщена: %s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    private Set<Integer> printListAllBooks() {
        System.out.println("Список книг: ");
        List<Book> books = bookFacade.findAll();
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder cbAutors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                cbAutors.append(books.get(i).getAuthor().get(j).getName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getLastname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() >= 0){
                System.out.printf("%d. %s. %s В наличии экземпряров: %d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
            }else if(books.get(i) != null && books.get(i).getCount() >= 0){
                System.out.printf("%d. %s. %s Нет наличии. Будет возварщена: %s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getCaption()
                        ,cbAutors.toString()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    private String getReturnDate(Book book){
        History history = historyFacade.findHistoryByGivenBook(book);
        LocalDate localGivenDate = history.getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localGivenDate = localGivenDate.plusDays(14);
        return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    private Set<Integer> printListReaders() {
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей: ");
        List<Reader> readers = readerFacade.findAll();
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s %s Телефон: %s%n"
                        ,readers.get(i).getId()
                        ,readers.get(i).getFirstname()
                        ,readers.get(i).getLastname()
                        ,readers.get(i).getPhone()
                );
                setNumbersReaders.add(readers.get(i).getId().intValue());
            }
        }
        if(setNumbersReaders.isEmpty()){
            System.out.println("Список читателей пуст");
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
        History history = historyFacade.find((long)historyNumber);
        history.setReturnDate(c.getTime());
        Book book = history.getBook();
        book.setCount(book.getCount()+1);
        bookFacade.edit(book);
        historyFacade.edit(history);
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

    private void addAuthor() {
         System.out.println("Добавление автора ");
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
            authorFacade.create(author);
    }

    private Set<Integer> printListAuthors() {
         Set<Integer> setNumbersAuthors = new HashSet<>();
        System.out.println("Список авторов: ");
        List<Author> authors = authorFacade.findAll();
        for (int i = 0; i < authors.size(); i++) {
            if(authors.get(i) != null){
                System.out.printf("%d. %s %s%n"
                        ,authors.get(i).getId()
                        ,authors.get(i).getName()
                        ,authors.get(i).getLastname()
                );
                setNumbersAuthors.add(authors.get(i).getId().intValue());
            }
        }
        return setNumbersAuthors;
    }

    private void updateBook() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        System.out.println("----------- Изменение названия книги ----------");
        Set<Integer> setNumbersBooks = printListAllBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.println("Выберите номер книги для изменения: ");
        int numberBook = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)numberBook);
        System.out.println("Название книги: "+ book.getCaption());
        System.out.println("Если хотите изменить, нажмите 1, пропустить 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое название книги: ");
            book.setCaption(scanner.nextLine());
        }
        System.out.println("Год издания книги: "+ book.getPublishedYear());
        System.out.println("Если хотите изменить, нажмите 1, пропустить 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый год издания книги: ");
            book.setPublishedYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: "+ book.getQuantity());
        System.out.println("Если хотите изменить, нажмите 1, пропустить 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров книги: ");
            int oldQuantity = book.getQuantity();
            int oldCount = book.getCount();
            int newQuantity;
            do {                
                newQuantity=getNumber();
                if(newQuantity >= 0 && newQuantity >= oldQuantity-oldCount){
                    break;
                }
                System.out.println("Попробуй еще: ");
            } while (true);
            book.setQuantity(newQuantity);
            int newCount = oldCount + (newQuantity - oldQuantity);
            book.setCount(newCount);
        }
        System.out.println("Авторы книги: ");
        for (int i = 0; i < book.getAuthor().size(); i++) {
            System.out.printf("%d. %s %s %d%n"
                    ,i+1
                    ,book.getAuthor().get(i).getName()
                    ,book.getAuthor().get(i).getLastname()
                    ,book.getAuthor().get(i).getYear()
            );
        }
        System.out.println("Если хотите изменить, нажмите 1, пропустить 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            book.getAuthor().clear();
            Set<Integer> setNumbersAuthors = printListAuthors();
            if(setNumbersAuthors.isEmpty()){
                return;
            }
            System.out.println("Введите новое количество авторов книги: ");
            int countAuthors;
            do {                
                countAuthors = getNumber();
                if(countAuthors > 0){
                    break;
                }
                System.out.println("Попробуй еще: ");
            } while (true);
            List<Author> bookAurhors = new ArrayList<>();
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Выберите number "+(i+1)+"-го автора:");
                int numAuthor = insertNumber(setNumbersAuthors);
                bookAurhors.add(authorFacade.find((long)numAuthor));
            }
            bookFacade.edit(book);
        }
        
    }
    
}
