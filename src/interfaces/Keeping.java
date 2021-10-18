/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Book;
import entity.Reader;
import java.util.List;

/**
 *
 * @author pupil
 */
public interface Keeping {
    public void saveBooks();
    public List<Book> loadBooks();
    public void saveReaders();
    public List<Reader> loadReaders();
    public void saveHistories();
    public List<History> loadHistories();
}
