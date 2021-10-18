/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeper;

import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pupil
 */
public class FileKeeper implements Keeping{

    @Override
    public void saveBooks() {
    
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> listBooks = new ArrayList<>();
        
        return listBooks;
    }

    @Override
    public void saveReaders() {
        
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> listReaders = new ArrayList<>();
    
        return listReaders;
    }

    @Override
    public void saveHistories() {
        
    }

    @Override
    public List<History> loadHistories() {
        List<History> listHistories = new ArrayList<>();
    
        return listHistories;
    }
    

}
