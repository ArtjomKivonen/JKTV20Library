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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class FileKeeper implements Keeping{

    @Override
    public void saveBooks(List<Book> books) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("books");

                oos = new ObjectOutputStream(fos);
                oos.writeObject(books);
                oos.flush();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "ошибка ввода", ex);
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> listBooks = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("books");
            ois = new ObjectInputStream(fis);
            listBooks = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "класс не обнаружен", ex);
        }
        return listBooks;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("readers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "ошибка ввода", ex);
        }
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> listReaders = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("readers");
            ois = new ObjectInputStream(fis);
            listReaders = (List<Reader>) ois.readObject();
        }
        catch (FileNotFoundException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        }
        catch (IOException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
        catch (ClassNotFoundException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "класс не обнаружен", ex);
        }
        
        return listReaders;
    }

    @Override
    public void saveHistories(List<History> histories) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("histories");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "ошибка ввода", ex);
        }
    }

    @Override
    public List<History> loadHistories() {
        List<History> listHistories = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("histories");
            ois = new ObjectInputStream(fis);
            listHistories = (List<History>) ois.readObject();
        }
        catch (FileNotFoundException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        }
        catch (IOException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
        catch (ClassNotFoundException ex) {
             Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "класс не обнаружен", ex);
        }
        
        return listHistories;
    }

    

}
