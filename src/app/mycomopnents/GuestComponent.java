/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import app.GuiApp;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class GuestComponent extends JPanel{
    private ListBooksComponent listBooksComponent;
   
    public GuestComponent() {
        setPreferredSize(new Dimension(GuiApp.WITH_WINDOWS,GuiApp.HEIGHT_WINDOWS-100));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        initComponents();
    }

    private void initComponents() {
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        listBooksComponent = new ListBooksComponent(true,"Список книг библиотеки", GuiApp.WITH_WINDOWS,GuiApp.HEIGHT_WINDOWS-100,GuiApp.WITH_WINDOWS-20);
//        listBooksComponent.getJList().setModel(listBooksComponent.getListModel(true));
//        listBooksComponent.getJList().setCellRenderer(listBooksComponent.createListBooksRenderer());
        this.add(listBooksComponent);
        
        
    }

    public ListBooksComponent getListBooksComponent() {
        return listBooksComponent;
    }

   

   

    
}
