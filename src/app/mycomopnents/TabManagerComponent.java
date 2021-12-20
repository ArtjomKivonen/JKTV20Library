/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import entity.Author;
import entity.Book;
import facade.BookFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class TabManagerComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditorComponent bookNameComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private EditorComponent publishedYearComponent;
    private EditorComponent quantityComponent;
    private ButtonComponent buttonComponent;
    public TabManagerComponent(int widthWindow) {
        initComponents(widthWindow);
    }

    private void initComponents(int widthWindow) {
        this.setPreferredSize(new Dimension(widthWindow,450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Добавление новой книги", widthWindow, 31);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", widthWindow, 31);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponent = new EditorComponent("Название книги", widthWindow, 31, 300);
        this.add(bookNameComponent);
        listAuthorsComponent = new ListAuthorsComponent("Авторы", widthWindow, 120, 300);
        this.add(listAuthorsComponent);
        publishedYearComponent = new EditorComponent("Год издания", widthWindow, 31, 100);
        this.add(publishedYearComponent);
        quantityComponent = new EditorComponent("Количество экземпляров", widthWindow, 31, 50);
        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Добавить книгу", widthWindow, 31, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonAddBook());
    }
    private ActionListener clickToButtonAddBook(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                /**
                 * Создать объект book
                 * Инициировать поля книги используя элементы компотентов
                 * Добавить книгу в базу данных
                 * Сообзить пользователю о результате 
                 * если true очистить редакторы компонентов
                 */
                Book book = new Book();
                if(bookNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите название книги");
                    return;
                }
                book.setCaption(bookNameComponent.getEditor().getText());
                try {
                    book.setPublishedYear(Integer.parseInt(publishedYearComponent.getEditor().getText()));
                } catch (Exception e) {
                    infoComponent.getInfo().setText("Год издания введите цифрами");
                    return;
                }
                try {
                    book.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                    book.setCount(book.getQuantity());
                } catch (Exception e) {
                    infoComponent.getInfo().setText("Количество введите цифрами");
                    return;
                }
                List<Author> bookAuthors = listAuthorsComponent.getJList().getSelectedValuesList();
                if(bookAuthors.isEmpty()){
                    infoComponent.getInfo().setText("Вы не выбрали авторов");
                    return;
                }
                book.setAuthor(bookAuthors);
                BookFacade bookFacade = new BookFacade(Book.class);
                try {
                    bookFacade.create(book);
                    infoComponent.getInfo().setText("Книга успешно добавлена");
                    listAuthorsComponent.getJList().clearSelection();
                    quantityComponent.getEditor().setText("");
                    publishedYearComponent.getEditor().setText("");
                    bookNameComponent.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setText("Книгу добавить не удалось");
                }
            }
        };
    }
}
