/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomopnents.TabDirectorComponent;
import app.mycomopnents.TabManagerComponent;
import app.mycomopnents.TabReaderComponents;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Melnikov
 */
public class GuiApp extends JFrame{
    public static final int WITH_WINDOWS = 600;
    public static final int HEIGHT_WINDOWS = 450;
    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setTitle("JPTV20 Library");
        this.setPreferredSize(new Dimension(WITH_WINDOWS,HEIGHT_WINDOWS));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setPreferredSize(new Dimension(WITH_WINDOWS,HEIGHT_WINDOWS));
        jTabbedPane.setMinimumSize(jTabbedPane.getPreferredSize());
        jTabbedPane.setMaximumSize(jTabbedPane.getPreferredSize());
        TabReaderComponents tabReaderComponents = new TabReaderComponents(this.getWidth());
        jTabbedPane.addTab("Читатель", tabReaderComponents);
        TabManagerComponent tabManagerComponent = new TabManagerComponent(this.getWidth());
        jTabbedPane.addTab("Библиотекарь", tabManagerComponent);
        this.getContentPane().add(jTabbedPane);
        TabDirectorComponent tabDirectorComponent = new TabDirectorComponent(this.getWidth());
        jTabbedPane.addTab("Директор", tabDirectorComponent);
        
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

}
