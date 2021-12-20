/*
Для отображения utf8 в консоли, отредактируйте файл netbeans.conf из etc каталога,
расположенного в каталоге установки NetBeans. 
В netbeans_default_options опции добавить -J-Dfile.encoding=UTF-8
 */
package keeper;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class BaseKeeper implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveBooks(List<Book> books) {
        tx.begin();
            for (int i = 0; i < books.size(); i++) {
                if(books.get(i).getId() == null){
                    em.persist(books.get(i));
                }else{
                    em.merge(books.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = null;
        try {
            books = em.createQuery("SELECT book FROM Book book")
                    .getResultList();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
        return books;
    }

    @Override
    public void saveAuthors(List<Author> authors) {
        tx.begin();
            for (int i = 0; i < authors.size(); i++) {
                if(authors.get(i).getId() == null){
                    em.persist(authors.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Author> loadAuthors() {
        List<Author> authors = null;
        try {
            authors = em.createQuery("SELECT author FROM Author author")
                    .getResultList();
        } catch (Exception e) {
            authors = new ArrayList<>();
        }
        return authors;
    }
    @Override
    public void saveReaders(List<Reader> readers) {
        tx.begin();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i).getId() == null){
                    em.persist(readers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> readers = null;
        try {
            readers = em.createQuery("SELECT readers FROM Reader readers")
                    .getResultList();
        } catch (Exception e) {
            readers = new ArrayList<>();
        }
        return readers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = null;
        try {
            histories = em.createQuery("SELECT histories FROM History histories")
                    .getResultList();
        } catch (Exception e) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    
}
