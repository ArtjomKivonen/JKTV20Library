/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import app.GuiApp;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class TabTakeOnBooksComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private ListBooksComponent listBooksComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxModel comboBoxModel;
    private Reader reader;
    private HistoryFacade historyFacade = new HistoryFacade();
    private BookFacade bookFacade = new BookFacade();
    
    public TabTakeOnBooksComponents(int widthPanel) {
        setPreferredSize(new Dimension(GuiApp.WITH_WINDOWS-5,GuiApp.HEIGHT_WINDOWS));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setComboBoxModel();
        initComponents(widthPanel);
    }
    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Выбор книги", widthPanel, 31);
        this.add(captionComponent); 
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", widthPanel, 30, 300);
        comboBoxReadersComponent.getComboBox().setModel(comboBoxModel);
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();
            }
        });
        this.add(comboBoxReadersComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        JCheckBox checkBoxAllBooks = new JCheckBox("Показать все книги");
        this.add(checkBoxAllBooks);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        listBooksComponent = new ListBooksComponent("Книги", widthPanel, 120, 300);
        this.add(listBooksComponent);
        checkBoxAllBooks.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    listBooksComponent.getJList().setModel(listBooksComponent.getListModel(true));
                    buttonComponent.getButton().setEnabled(false);
                    listBooksComponent.getJList().setEnabled(false);
                }else{
                    listBooksComponent.getJList().setModel(listBooksComponent.getListModel(false));
                    buttonComponent.getButton().setEnabled(true);
                    listBooksComponent.getJList().setEnabled(true);
                }
            }
        });
        this.add(Box.createRigidArea(new Dimension(0,10)));
        buttonComponent = new ButtonComponent("Взять книгу для чтения", widthPanel, 35, widthPanel/3+5, 300);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonTakeOnBook());
    }
    private ActionListener clickToButtonTakeOnBook(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (comboBoxReadersComponent.getComboBox().getSelectedIndex() == -1){
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Вы не выбрали читателя");
                    return;
                }
                List<Book> books = listBooksComponent.getJList().getSelectedValuesList();
                if (books.isEmpty()){
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Вы не выбрали книгу");
                    return;
                }
                try {
                    for (Book book : books) {
                      History history = new History();
                      book.setCount(book.getCount()-1);
                      bookFacade.edit(book);
                      history.setBook(book);
                      history.setReader(reader);
                      Calendar c = new GregorianCalendar();
                      history.setGivenDate(c.getTime());
                      historyFacade.create(history);
                      infoComponent.getInfo().setForeground(Color.GREEN);
                      infoComponent.getInfo().setText("Выбранные книги выданы");
                      comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
                      listBooksComponent.getJList().setModel(listBooksComponent.getListModel(false));
                      listBooksComponent.getJList().clearSelection();
                      
                    }
                } catch (Exception e) {
                      infoComponent.getInfo().setForeground(Color.RED);
                      infoComponent.getInfo().setText("Выбранные книги выдать не удалось");

                }
                
               
            }
        };
    }
    private void setComboBoxModel(){
        ReaderFacade readerFacade = new ReaderFacade();
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel=defaultComboBoxModel;
    }
    public void addComboBoxModel() {
        infoComponent.getInfo().setText("");
        setComboBoxModel();
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
    }
    
    
    
}
