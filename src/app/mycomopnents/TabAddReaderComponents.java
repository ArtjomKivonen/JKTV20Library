/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomopnents;

import app.GuiApp;
import entity.Reader;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class TabAddReaderComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    public TabAddReaderComponents(int widthPanel) {
        setPreferredSize(new Dimension(GuiApp.WITH_WINDOWS-5,GuiApp.HEIGHT_WINDOWS));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Добавление новой читателя", widthPanel, 31);
        this.add(captionComponent); 
       infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditorComponent("Имя", widthPanel, 31, 300);
        this.add(nameComponent);
        lastNameComponent = new EditorComponent("Фамилия", widthPanel, 31, 300);
        this.add(lastNameComponent);
        phoneComponent = new EditorComponent("Телефон", widthPanel, 31, 200);
        this.add(phoneComponent);
        buttonComponent = new ButtonComponent("Добавить читателя", widthPanel, 31, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonAddReader());
    }
    private ActionListener clickToButtonAddReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Reader reader = new Reader();
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите имя");
                    return;
                }
                reader.setFirstname(nameComponent.getEditor().getText());
                if(lastNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите фамилию");
                    return;
                }
                reader.setLastname(lastNameComponent.getEditor().getText());
                
                if(phoneComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите телефон");
                    return;
                } 
                reader.setPhone(phoneComponent.getEditor().getText());
                
                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                
                try {
                    readerFacade.create(reader);
                    infoComponent.getInfo().setText("Читатель успешно добавлен");
                    phoneComponent.getEditor().setText("");
                    lastNameComponent.getEditor().setText("");
                    nameComponent.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setText("Читателя добавить не удалось");
                }
            }
        };
    }
    
    
}
