/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import entity.Reader;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Melnikov
 */
public class TabDirectorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxModel comboBoxModel;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    
    public TabDirectorComponent(int widthWindow) {
        setComboBoxModel();
        initComponents(widthWindow);
    }

    private void initComponents(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel,450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabDirector = new JTabbedPane();
        tabDirector.setPreferredSize(new Dimension(widthPanel-17,450));
        tabDirector.setMinimumSize(tabDirector.getPreferredSize());
        tabDirector.setMaximumSize(tabDirector.getPreferredSize());
        tabDirector.setAlignmentX(CENTER_ALIGNMENT);
        TabAddReaderComponents tabAddReaderComponents = new TabAddReaderComponents(widthPanel);
        tabDirector.addTab("Регистрация читателя", tabAddReaderComponents);
//        comboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", widthPanel, 30, 300);
        TabEditReaderComponents tabEditReaderComponents = new TabEditReaderComponents(widthPanel);
        tabDirector.addTab("Изменить читателя", tabEditReaderComponents);
        this.add(tabDirector);
        tabDirector.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                tabEditReaderComponents.addComboBoxModel();
            }
        });
    }  
    private void setComboBoxModel(){
        ReaderFacade readerFacade = new ReaderFacade();
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel = defaultComboBoxModel;
    }
}
